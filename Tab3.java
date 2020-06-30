package com.example.drn20;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {


    // this tab is for showing personal information

    private View view1;
    private EditText account_name;
    private EditText telephoneNumber;
    private TextView email_name;
    private Switch notification;
    private TextView passwordNew2;
    private TextView passwordNew;
    private TextView logOut;
    private Button saveChanges;
    private ProgressBar progressBar;


    private FirebaseAuth myAuth;
    FirebaseAuth.AuthStateListener myAuthStateLitener1;
    FirebaseUser firebaseUser1;
    DatabaseReference myRef1;
    FirebaseDatabase database1;


    String userID1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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
        view1 = inflater.inflate(R.layout.fragment_tab3 , container ,false);
        account_name = view1.findViewById(R.id.userNameInfo);
        email_name = view1.findViewById(R.id.email_accountInfo);
        notification = view1.findViewById(R.id.notification_toggle);
        passwordNew = view1.findViewById(R.id.passwordNew);
        passwordNew2 = view1.findViewById(R.id.passwordNew2);
        logOut = view1.findViewById(R.id.accountSingOut);
        saveChanges = view1.findViewById(R.id.saveChanges);
        progressBar = view1.findViewById(R.id.account_progress);
        telephoneNumber = view1.findViewById(R.id.telephone_Info);


        //Firebase
        myAuth = FirebaseAuth.getInstance();
        database1 = FirebaseDatabase.getInstance();

        firebaseUser1 = myAuth.getCurrentUser();
        myRef1 = database1.getReference();

        userID1 = firebaseUser1.getUid();

        myRef1 = FirebaseDatabase.getInstance().getReference().child(firebaseUser1.getUid());

        //set user info
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                account_name.setText(Objects.requireNonNull(dataSnapshot.child("isim").getValue()).toString());
                email_name.setText(Objects.requireNonNull(dataSnapshot.child("email").getValue()).toString());
                telephoneNumber.setText(Objects.requireNonNull(dataSnapshot.child("telephone").getValue()).toString());
                if(telephoneNumber.getText() == null){
                    telephoneNumber.setHint("Telefon numaranız");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //entry activity
        account_name.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(account_name.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );


        email_name.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(account_name.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );

        telephoneNumber.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View arg0, int arg1, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
                        (arg1           == KeyEvent.KEYCODE_ENTER)   )
                {
                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(account_name.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        } );


        /// for singout action
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
                /*Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                myAuth.signOut();*/
            }
        });
        ///////
        // change user name and save changes
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String username = account_name.getText().toString();
                String telephone = telephoneNumber.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Lütfen kullanıcı adı giriniz", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                myRef1 = FirebaseDatabase.getInstance().getReference().child(userID1).child("isim");
                myRef1.setValue(username);
                myRef1 = FirebaseDatabase.getInstance().getReference().child(userID1).child("telephone");
                myRef1.setValue(telephone);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Değişiklikler Kaydedildi.", Toast.LENGTH_SHORT).show();
            }
        });
        ////
        // password security
        passwordNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPasswordActivity.class);
                startActivity(intent);
            }
        });

        passwordNew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPasswordActivity.class);
                startActivity(intent);
            }
        });
        /////

        return view1;
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

    private void showExitDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));

        LayoutInflater inf = getLayoutInflater();

        final  View dialogView = inf.inflate(R.layout.exit_dialog , null);

        dialogBuilder.setView(dialogView);

        final TextView vazgec = dialogView.findViewById(R.id.vazgec_button);
        final TextView exit_dialog = dialogView.findViewById(R.id.exit_dialog);

        dialogBuilder.setTitle("Bilgi");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        vazgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                return;
            }
        });

        exit_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
                myAuth.signOut();
            }
        });


    }
}
