package com.example.katecatlin.finalproject.parsers;

import com.example.katecatlin.finalproject.models.ConcertModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 11/30/14.
 */
public class JSONParser {

    public static List <ConcertModel> parseJSONObject (JSONObject jsonObject) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("Events");

            List <ConcertModel> concertsList = new ArrayList<ConcertModel>();

            for (int index = 0; index < jsonArray.length(); index++) {

                JSONObject concertEntry = jsonArray.getJSONObject(index);

                ConcertModel newConcertModel = createConcertModelFromJsonInfo (concertEntry);

                concertsList.add(newConcertModel);

            }

            return  concertsList;

        } catch (JSONException e) {
           return new ArrayList<ConcertModel>();
        }

    }

    public static ConcertModel createConcertModelFromJsonInfo (JSONObject newConcertModel) {

        ConcertModel concertModel;

        return concertModel;
    }
}
