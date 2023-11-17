package dev.voper.autoupdatetest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class AutoUpdater implements Runnable {

    private static final String LATEST_RELEASE = "https://api.github.com/repos/VoperAD/auto-update-testing/releases/latest";

    private final AutoUpdateTest plugin;
    private final Logger logger;
    private final String version;
    private final File file;

    public AutoUpdater(AutoUpdateTest plugin, File file) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.version = plugin.getDescription().getVersion();
        this.file = file;
    }

    @Override
    public void run() {
        checkLatestVersion();
    }

    private void checkLatestVersion() {
        try {
            URL urlLatestRelease = new URL(LATEST_RELEASE);
            HttpURLConnection connection = (HttpURLConnection) urlLatestRelease.openConnection();

            connection.addRequestProperty("User-Agent", "TropaDoCavalinho");

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JsonElement jsonElement = JsonParser.parseReader(inputStreamReader);
            JsonObject obj = jsonElement.getAsJsonObject();

            String tagName = obj.get("tag_name").getAsString();
            logger.info("tag_name = " + tagName);

            JsonArray assetsArray = obj.getAsJsonArray("assets");
            JsonObject asset = assetsArray.get(0).getAsJsonObject();
            String jarName = asset.get("name").getAsString();
            logger.info("jar name = " + jarName);

            BufferedInputStream in = null;
            FileOutputStream fout = null;

            try {
                URL downloadUrl = new URL("https://github.com/VoperAD/auto-update-testing/releases/download/" + tagName + "/" + jarName);
                in = new BufferedInputStream(downloadUrl.openStream());
                fout = new FileOutputStream(new File(plugin.getServer().getUpdateFolderFile(), file.getName()));

                final byte[] data = new byte[4096];
                int count;
                while ((count = in.read(data, 0, 4096)) != -1) {
                    fout.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    fout.close();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download() {

    }



}
