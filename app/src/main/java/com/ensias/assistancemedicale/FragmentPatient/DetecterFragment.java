
package com.ensias.assistancemedicale.FragmentPatient;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ensias.assistancemedicale.R;

public class DetecterFragment extends Fragment {


    Context mContext;

    public DetecterFragment() {
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detecter,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(),"Detecter objet",Toast.LENGTH_LONG);
    }
}
