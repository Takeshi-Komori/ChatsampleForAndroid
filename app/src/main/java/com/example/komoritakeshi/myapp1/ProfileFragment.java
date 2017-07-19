package com.example.komoritakeshi.myapp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM = "page";
    private String mParam;
    private ProfileFragment.OnFragmentInteractionListener mListener;
    private MyListener myListener;

    public interface MyListener {
        public void onClickButton();
    }

    TextView nameTextView;
    TextView genderAndAgeTextView;
    TextView regionTextView;
    TextView bioTextView;

    // コンストラクタ
    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(int page) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int page = getArguments().getInt(ARG_PARAM, 0);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = (TextView) view.findViewById(R.id.name_prof);
        genderAndAgeTextView = (TextView) view.findViewById(R.id.gender_age_prof);
        regionTextView = (TextView) view.findViewById(R.id.region_prof);
        bioTextView = (TextView) view.findViewById(R.id.biography_prof);

        Button profileEditBtn = (Button) view.findViewById(R.id.profileEditBtn);
        profileEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myListener != null) {
                    myListener.onClickButton();
                }
            }
        });

        setValue();
        return view;
    }

    void setValue() {
        if (Me.getInstance().isRegisterd(getActivity())) {
            nameTextView.setText(Me.getInstance().getNameFromLocal(getActivity()));
            genderAndAgeTextView.setText(Me.getInstance().getGenderFromLocal(getActivity()) + " / "
                    + Me.getInstance().getAgeFromLocal(getActivity()));
            regionTextView.setText(Me.getInstance().getRegionFromLocal(getActivity()));
            bioTextView.setText(Me.getInstance().getBiographyFromLocal(getActivity()));
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragment.OnFragmentInteractionListener) {
            mListener = (ProfileFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        if (context instanceof MyListener) {
            myListener = (MyListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        myListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
