package com.example.android.group14_inclass07;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectAvatar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectAvatar#newInstance} factory method to
 * create an instance of this fragment.
 */
//Jeremy Bohannon Elizabeth Thompson
//InClass07
//slectavatar.java
public class SelectAvatar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectAvatar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectAvatar.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectAvatar newInstance(String param1, String param2) {
        SelectAvatar fragment = new SelectAvatar();
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
        return inflater.inflate(R.layout.fragment_select_avatar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.f1).setOnClickListener(onImageClick);
        getActivity().findViewById(R.id.f2).setOnClickListener(onImageClick);
        getActivity().findViewById(R.id.f3).setOnClickListener(onImageClick);
        getActivity().findViewById(R.id.m1).setOnClickListener(onImageClick);
        getActivity().findViewById(R.id.m2).setOnClickListener(onImageClick);
        getActivity().findViewById(R.id.m3).setOnClickListener(onImageClick);

    }

    View.OnClickListener onImageClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("Debug", "onClick: " + view.getId());
            int image = -1;

            switch (view.getId()) {
                case R.id.f1:
                    image = R.drawable.avatar_f_1;
                    break;
                case R.id.f2:
                    image = R.drawable.avatar_f_2;
                    break;
                case R.id.f3:
                    image = R.drawable.avatar_f_3;
                    break;
                case R.id.m1:
                    image = R.drawable.avatar_m_1;
                    break;
                case R.id.m2:
                    image = R.drawable.avatar_m_2;
                    break;
                case R.id.m3:
                    image = R.drawable.avatar_m_3;
                    break;
            }
            mListener.onSelectFragmentInteraction(image);
        }
    };

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
        void onSelectFragmentInteraction();
        void onSelectFragmentInteraction(int image);
    }
}
