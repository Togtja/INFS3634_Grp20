package com.example.grp20_app;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
/*
class WikiPageDestabilizer implements JsonDeserializer<WikiPage> {

    @Override
    public WikiPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jobj = json.getAsJsonObject().get("query").getAsJsonObject().get("pages").getAsJsonObject().get(id);
        WikiPage page(jobj.get());
    }
}
*/