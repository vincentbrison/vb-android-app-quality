/*
 * Copyright 2015 Vincent Brison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vb.android.app.quality;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper to load flat file into strings.
 */
public final class AssetsHelper {
    private AssetsHelper() {
    }

    /**
     * Return the content of a given asset as a plain string.
     *
     * @param assetPath is th e path of the given asset (like "file.json", or "dir/file.json").
     * @return the content of the given asset as a plain string.
     * @throws IOException if needed.
     */
    public static String getStringFromAsset(String assetPath) throws IOException {
        StringBuilder buf = new StringBuilder();
        InputStream inputStream = null;
        String str = null;

        try {
            inputStream = InjectorHelper.getApplicationComponent().context().getAssets().open(assetPath);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            inputStream.close();
            in.close();
        } catch (IOException e) {
            throw e;
        }

        return buf.toString();
    }
}
