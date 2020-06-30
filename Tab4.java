package com.example.drn20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab4 extends Fragment {

    //this tab is for displaying other infos

    View view4;
    private LinearLayout help_button;
    private LinearLayout aboutUs;
    private LinearLayout sendProblem;
    private LinearLayout versionButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab4.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab4 newInstance(String param1, String param2) {
        Tab4 fragment = new Tab4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view4 = inflater.inflate(R.layout.fragment_tab4 , container ,false);
        help_button = view4.findViewById(R.id.help_button);
        aboutUs = view4.findViewById(R.id.aboutUs_button);
        sendProblem = view4.findViewById(R.id.sendProblem_button);
        versionButton = view4.findViewById(R.id.version_button);

        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpPage.class);
                startActivity(intent);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsPage.class);
                startActivity(intent);
            }
        });

        sendProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAppVersion();
            }
        });


        return view4;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void sendEmail() {

        String to= "info@kargomkolay.com" ;
        String subject = "Sorun Bildir";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL ,new String[]{ to});
        intent.putExtra(Intent.EXTRA_SUBJECT , subject);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent , "Choose an email client"));

    }

    private void checkAppVersion() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int currentAppVersionCode = getCurrentAppVersionCode();
        int oldAppVersion = prefs.getInt("app_version", 0);
        if (oldAppVersion < currentAppVersionCode) {
            try {
                if(oldAppVersion > 0){
                    Toast.makeText(getActivity(), String.format("App updated from version %d", oldAppVersion), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), String.format("App started for the first time", oldAppVersion), Toast.LENGTH_SHORT).show();
                }
            } finally {
                SharedPreferences.Editor preferencesEditor = prefs.edit();
                preferencesEditor.putInt("app_version", currentAppVersionCode);
                preferencesEditor.commit();
            }
        }
        else if(oldAppVersion == currentAppVersionCode){
            Toast.makeText(getActivity(), String.format("Son versiyonu kullanıyorsunuz.", oldAppVersion), Toast.LENGTH_SHORT).show();
        }
    }

    private int getCurrentAppVersionCode() {
        int versionCode = 1;
        try {
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
