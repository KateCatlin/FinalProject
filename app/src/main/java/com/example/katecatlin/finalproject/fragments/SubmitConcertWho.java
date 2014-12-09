package com.example.katecatlin.finalproject.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.katecatlin.finalproject.R;
import com.example.katecatlin.finalproject.interfaces.FragmentController;
import com.example.katecatlin.finalproject.models.ConcertModel;

/**
 * Created by katecatlin on 12/3/14.
 */
public class SubmitConcertWho extends Fragment {

    private ConcertModel submittedConcert;
    private EditText edit_artist_1, edit_artist_2, edit_artist_3;
    private ImageButton next_button;
    private Button button_picture_choose;
    static final String SUBMITTED_CONCERT_ENTRY = "SUBMITTED_CONCERT_ENTRY";
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;


    public static SubmitConcertWho newInstance(ConcertModel concertModel){
        Bundle args = new Bundle();
        args.putParcelable(SUBMITTED_CONCERT_ENTRY, concertModel);

        SubmitConcertWho submitConcertWho = new SubmitConcertWho();
        submitConcertWho.setArguments(args);
        return submitConcertWho;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submit_concert_who, container, false);
        submittedConcert = getArguments().getParcelable(SUBMITTED_CONCERT_ENTRY);
        edit_artist_1 = (EditText) view.findViewById(R.id.edit_artist_1);
        edit_artist_2 = (EditText) view.findViewById(R.id.edit_artist_2);
        edit_artist_3 = (EditText) view.findViewById(R.id.edit_artist_3);
        button_picture_choose = (Button) view.findViewById(R.id.button_picture_choose);
        next_button = (ImageButton) view.findViewById(R.id.button_next);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        button_picture_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edit_artist_1.getText().toString() != "") {

                    String artist1 = edit_artist_1.getText().toString();
                    String artist2 = edit_artist_2.getText().toString();
                    String artist3 = edit_artist_3.getText().toString();
                    submittedConcert.setArtist1(artist1);
                    submittedConcert.setArtist2(artist2);
                    submittedConcert.setArtist3(artist3);

                    SubmitConcertWhere submitConcertWhere = SubmitConcertWhere.newInstance(submittedConcert);

                    FragmentController fragmentController = (FragmentController) getActivity();
                    fragmentController.changeFragment(submitConcertWhere, true);

                } else {
                    Toast toast = Toast.makeText(getActivity(), "Enter in a headlining artist to proceed!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }


    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }
}
