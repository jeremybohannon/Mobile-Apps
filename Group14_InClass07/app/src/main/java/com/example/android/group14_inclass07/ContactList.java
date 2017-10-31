package com.example.android.group14_inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
//Jeremy Bohannon Elizabeth Thompson
//InClass07
//contactlist.java

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<Contact> mParam1;

    ArrayList<Contact> contacts;
    private OnFragmentInteractionListener mListener;
    ListView lv;
    CustomAdapter customAdapter;

    public ContactList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ContactList.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactList newInstance(ArrayList<Contact> contacts) {
        ContactList fragment = new ContactList();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Debug", "OnActivityCreated: " );

        contacts = (ArrayList<Contact>) getArguments().getSerializable(ARG_PARAM1);

        if(contacts != null){
            Log.d("Debug", "OnActivityCreated: " + contacts.size());
            lv = getActivity().findViewById(R.id.contactView);

            customAdapter = new CustomAdapter(getActivity(), R.layout.fragment_contact_list, contacts);
            lv.setAdapter(customAdapter);
            lv.setLongClickable(true);
            lv.setOnItemLongClickListener(longListener);

        }

        getActivity().findViewById(R.id.newContactBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction();
            }
        });

    }

    AdapterView.OnItemLongClickListener longListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            contacts.remove(i);
            customAdapter.notifyDataSetChanged();

            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ArrayList<Contact>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
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
        void onListFragmentInteraction();
        void onListFragementInteraction(int index);
    }
}
