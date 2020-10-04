package br.maua.classes.parser;

import br.maua.classes.Anime;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimeParser {
    public static Anime parseJson(String json){
        JSONObject meuJson = new JSONObject(json);
        if(meuJson.getString("status").equals("success"))
            return new Anime(
                    meuJson.getJSONObject("data").getString("url"),
                    meuJson.getJSONObject("data").getString("title"),
                    meuJson.getJSONObject("data").getString("sinopsis"),
                    meuJson.getJSONObject("data").getInt("episodes"),
                    meuJson.getJSONObject("data").getFloat("score")
            );
        else return new Anime();
    }
}
