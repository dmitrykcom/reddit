package com.dmitryk.reddit.rest;

import android.text.Html;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class PostDeserializer<T> implements JsonDeserializer<T> {


    @Override
    public T deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonElement content = json.getAsJsonObject().get("data");

        String thumbnail = content.getAsJsonObject().get("thumbnail").toString();


        boolean isDefault = thumbnail.equals("\"default\"");


        content.getAsJsonObject().add("defaultThumbnail", extractThumbnail(content, isDefault));


        GsonBuilder builder = new GsonBuilder();
        return builder.create().fromJson(content, type);
    }

    private JsonElement extractThumbnail(JsonElement data, boolean isDefault) {
        JsonObject thumbnail = null;


        try {
            JsonArray resolutions = data.getAsJsonObject()
                    .get("preview").getAsJsonObject()
                    .get("images").getAsJsonArray().get(0).getAsJsonObject()
                    .get("resolutions").getAsJsonArray();


            // use best resolution for media posts
            int resolutionIndex = isDefault ? 0 : resolutions.size() - 1;
            String url = resolutions.get(resolutionIndex).getAsJsonObject().get("url").toString();
            url = Html.fromHtml(url).toString().replace("\"", "");
            String width = resolutions.get(resolutionIndex).getAsJsonObject().get("width").toString();
            String height = resolutions.get(resolutionIndex).getAsJsonObject().get("height").toString();

            thumbnail = new JsonObject();
            thumbnail.addProperty("url", url);
            thumbnail.addProperty("width", width);
            thumbnail.addProperty("height", height);


        } catch (Exception e) {
            return null;
        }
        return thumbnail.getAsJsonObject();
    }
}