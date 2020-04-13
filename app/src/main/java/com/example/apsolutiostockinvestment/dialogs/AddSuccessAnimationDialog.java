package com.example.apsolutiostockinvestment.dialogs;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.apsolutiostockinvestment.R;

public class AddSuccessAnimationDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.addsuccessdialog,container,false);
        LottieAnimationView lottieAnimationView=view.findViewById(R.id.addsuccessanimation);
        lottieAnimationView.setAnimation(R.raw.success1);
        lottieAnimationView.playAnimation();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {



        super.onDismiss(dialog);

    }
}
