package com.example.android.group14_inclass09;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateNewContact.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateNewContact#newInstance} factory method to
 * create an instance of this fragment.
 */
//Jeremy Bohannon Elizabeth Thompson
//InClass09
//createnewcontact.java
public class CreateNewContact extends Fragment implements SelectAvatar.OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1 = -1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreateNewContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param image Parameter 1.
     * @return A new instance of fragment CreateNewContact.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewContact newInstance(int image) {
        CreateNewContact fragment = new CreateNewContact();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            Log.d("Debug", "onCreate: " + mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_new_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("THIS IS OUR IMAGE ID:" + mParam1);
        ImageView test = getActivity().findViewById(R.id.avatar);
        if(mParam1 >= 0) {
            test.setImageResource(mParam1);
        }
        getActivity().findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCreateFragmentInteraction();
            }
        });

        getActivity().findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) getActivity().findViewById(R.id.name);
                EditText email = (EditText) getActivity().findViewById(R.id.email);
                EditText phone = (EditText) getActivity().findViewById(R.id.phone);
                RadioGroup grp = (RadioGroup) getActivity().findViewById(R.id.departmentGroup);

                int id = grp.getCheckedRadioButtonId();
                RadioButton dept = (RadioButton) getActivity().findViewById(id);

                Contact contact = new Contact(name.getText().toString(), email.getText().toString(), phone.getText().toString(), dept.getText().toString(), getArguments().getInt(
                        ARG_PARAM1));

                mListener.onCreateFragmentInteraction(contact);
            }
        });
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

    @Override
    public void onSelectFragmentInteraction() {

    }

    @Override
    public void onSelectFragmentInteraction(int image) {
        mParam1 = image;
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
        void onCreateFragmentInteraction();
        void onCreateFragmentInteraction(Contact contact);
    }
}
