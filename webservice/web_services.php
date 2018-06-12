<?php
  
   header('Content-Type: application/json; charset=utf-8');
    define('HOST','mysql:host=localhost;dbname=assistancemedicale');
    define('USER','root');
    define('PASS','');
    $bd = new PDO(HOST,USER,PASS, array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));

  function utf8_encode_all($dat) // -- It returns $dat encoded to UTF8
  {
    if (is_string($dat)) return utf8_encode($dat);
    if (!is_array($dat)) return $dat;
    $ret = array();
    foreach($dat as $i=>$d) $ret[$i] = utf8_encode_all($d);
    return $ret;
  }

    switch($_GET['action'])
    {
         case 'signin_patient':
        extract($_GET);
        $query = $bd->prepare("select * from patient where username = ? AND password = ?");
        $query->execute(array($username,$password));
        $result = $query->fetchAll();
        echo json_encode($result);
        break;

        case 'signin_medecin':
        extract($_GET);
        $query = $bd->prepare("select * from medecin where username = ? AND password = ?");
        $query->execute(array($username,$password));
        $result = $query->fetchAll();
        echo json_encode($result);
        break;

      case 'signup_patient':                
        extract($_POST);
        $query = $bd->prepare("insert into patient(nom,prenom,datenaissance,adresse,telephone,username,password) VALUES(:nom,:prenom,:datenaissance,:adresse,:telephone,:username,:password)");
        $query->execute(array('nom' => $nom,'prenom'=>$prenom,'datenaissance'=>$datenaissance,'adresse'=>$adresse,'telephone'=>$telephone,'username'=>$username,'password'=>$password));
      break;


       case 'signup_doctor':                
        extract($_POST);
        $query = $bd->prepare("insert into medecin(nom,prenom,specialite,adresse,telephone,username,password) VALUES(:nom,:prenom,:specialite,:adresse,:telephone,:username,:password)");
        $query->execute(array('nom' => $nom,'prenom'=>$prenom,'specialite'=>$specialite,'adresse'=>$adresse,'telephone'=>$telephone,'username'=>$username,'password'=>$password));
      break;



      case 'medecin_patient':
        extract($_GET);
        $query = $bd->prepare("select * from patient where id_medecin = ?");
        $query->execute(array($idmedecin));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;
      


      case 'AddPatient':
        extract($_POST);
        $query = $bd->prepare("update patient set id_medecin=? where id=?");
        $result = $query->execute(array($idmedecin,$id));
      break;



      case 'FindPatient':
        extract($_GET);
        $query = $bd->prepare("select * from patient where username = ?");
        $query->execute(array($username));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

       case 'FindPatientId':
        extract($_GET);
        $query = $bd->prepare("select * from patient where id = ?");
        $query->execute(array($id));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;



      case 'FindMedecinId':
        extract($_GET);
        $query = $bd->prepare("select * from medecin where id = ?");
        $query->execute(array($id));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

      case 'AllMedicament':

      extract($_GET);
        $query = $bd->prepare("select * from medicament where id>?");
        $query->execute(array(0));
        $result = $query->fetchAll();
        $result= utf8_encode_all($result);  
        echo json_encode($result);
        # code...
      break;
      

      case 'AddRDV':                
        extract($_GET);
        $query = $bd->prepare("insert into rdv(id_patient,Description,date) VALUES(:idpatient,:description,:date)");
        $query->execute(array('idpatient' => $idPatient,'description'=>$description,'date'=>$date));
        $result =null;
        echo json_encode($result);
      break;


      case 'FindRendezVousId':
        extract($_GET);
        $query = $bd->prepare("select * from RDV where id_patient = ? ORDER BY date DESC");
        $query->execute(array($idPatient));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;



      case 'FindAllRendezVous':
        extract($_GET);
        $query = $bd->prepare("
SELECT * from rdv a JOIN 
(SELECT id_patient,MAX(date) date FROM rdv GROUP by id_patient ) b
on a.id_patient = b.id_patient and a.date = b.date");

        $query->execute();
        $result = $query->fetchAll();
        echo json_encode($result);
      break;



      
  
  /*
  
      case 'products_vet':
        $query = $bd->prepare("select * from PRODUITS p,TYPES t where t.id_type=p.type and t.id_type=2");
        $query->execute();
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

      case 'newCommande':
        extract($_POST);
        $query = $bd->prepare("insert into COMMANDES(id_client,id_produit,qte,date_cmd,valider) VALUES(:id_client,:id_produit,:qte,:date_cmd,0)");
        $result = $query->execute(array('id_client' => $id_client ,'id_produit' => $id_produit,'qte'=>$qte,'date_cmd'=>$date_cmd ));
      break;

      case 'panier':
        extract($_GET);
        $query = $bd->prepare("select * from COMMANDES c,PRODUITS d where c.id_produit = d.id and c.id_client = :id_client and valider=0");
        $query->execute(array(':id_client' => $id_client));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

      case 'validerItem':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set valider=1 , etat=1, date_cmd=NOW() where id_cmd=:id_cmd");
        $query->execute(array(':id_cmd' => $id_cmd));
      break;

      case 'validerPanier':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set valider=1 , etat=1, date_cmd=NOW()  where id_client=:id_client");
        $query->execute(array(':id_client' => $id_client));
      break;

      case 'annulerCommande':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set etat=0 where id_cmd=:id_cmd");
        $query->execute(array(':id_cmd' => $id_cmd));
      break;

      case 'commandes':
        extract($_GET);
        $query = $bd->prepare("select * from COMMANDES c,PRODUITS d where c.id_produit = d.id and c.id_client = :id_client and valider=1");
        $query->execute(array(':id_client' => $id_client));
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

      case 'allCommandes':
        extract($_GET);
        $query = $bd->prepare("select * from COMMANDES s,CLIENTS t,PRODUITS p where s.id_client=t.id and s.id_produit=p.id order by date_cmd desc");
        $query->execute();
        $result = $query->fetchAll();
        echo json_encode($result);
      break;

      case 'prendreLivraison':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set etat = 2 where id_cmd = :id_cmd");
        $query->execute(array("id_cmd"=>$id_cmd));
      break;

      case 'confirmerLivraison':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set etat = 3 where id_cmd = :id_cmd");
        $query->execute(array("id_cmd"=>$id_cmd));
      break;

      case 'updatePanier':
        extract($_POST);
        $query = $bd->prepare("update COMMANDES set qte = :qte where id_cmd = :id_cmd");
        $query->execute(array("qte"=>$qte , "id_cmd"=>$id_cmd));
        break;

      case 'deletePanier':
        extract($_POST);
        $query = $bd->prepare("delete from COMMANDES where id_cmd = :id_cmd");
        $query->execute(array("id_cmd"=>$id_cmd));
      break;*/
    }
