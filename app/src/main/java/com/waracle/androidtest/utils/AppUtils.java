package com.waracle.androidtest.utils;



import com.waracle.androidtest.model.CakeModel;
import org.json.JSONArray;
import java.util.ArrayList;

public class AppUtils {

    public static String BASE_URL="https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/" +
            "raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";


    public static ArrayList<CakeModel> parseCakeListJsonArray(String jsonText) throws Exception {
        JSONArray array = new JSONArray(jsonText);
        ArrayList<CakeModel> cakes=new ArrayList<>();
        for(int x=0;x<array.length();x++)
        {
            CakeModel model=new CakeModel(array.getJSONObject(x).getString("title"),
                    array.getJSONObject(x).getString("desc"),
                    array.getJSONObject(x).getString("image"));

            cakes.add(model);
        }
        return cakes;
    }


}
