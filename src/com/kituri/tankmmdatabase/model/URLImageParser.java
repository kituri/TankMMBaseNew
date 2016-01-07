//package com.kituri.tankmmdatabase.model;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.AsyncTask;
//import android.text.Html.ImageGetter;
//import android.view.View;
//
//public class URLImageParser implements ImageGetter {
//    Context c;
//    View container;
//
//    /***
//     * Construct the URLImageParser which will execute AsyncTask and refresh the container
//     * @param t
//     * @param c
//     */
//    public URLImageParser(View t, Context c) {
//        this.c = c;
//        this.container = t;
//    }
//
//    public Drawable getDrawable(String source) {
//        URLDrawable urlDrawable = new URLDrawable();
//
//        // get the actual source
//        ImageGetterAsyncTask asyncTask = 
//            new ImageGetterAsyncTask( urlDrawable);
//
//        asyncTask.execute(source);
//
//        // return reference to URLDrawable where I will change with actual image from
//        // the src tag
//        return urlDrawable;
//    }
//
//    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>  {
//        URLDrawable urlDrawable;
//
//        public ImageGetterAsyncTask(URLDrawable d) {
//            this.urlDrawable = d;
//        }
//
//        @Override
//        protected Drawable doInBackground(String... params) {
//            String source = params[0];
//            return fetchDrawable(source);
//        }
//
//        @Override
//        protected void onPostExecute(Drawable result) {
//            // set the correct bound according to the result from HTTP call
//            urlDrawable.setBounds(0, 0, 0 + (int)(result.getIntrinsicWidth() * 1.5), 0 
//                    + (int)(result.getIntrinsicHeight() * 1.5) ); 
//
//            // change the reference of the current drawable to the result
//            // from the HTTP call
//            urlDrawable.drawable = result;
//
//            // redraw the image by invalidating the container
//            URLImageParser.this.container.invalidate();
//        }
//
//        /***
//         * Get the Drawable from URL
//         * @param urlString
//         * @return
//         */
//        public Drawable fetchDrawable(String urlString) {
//            try {
//                InputStream is = fetch(urlString);
//                Drawable drawable = Drawable.createFromStream(is, "src");
//                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth(), 0 
//                        + drawable.getIntrinsicHeight()); 
//                return drawable;
//            } catch (Exception e) {
//                return null;
//            } 
//        }
//
//        private InputStream fetch(String urlString) throws MalformedURLException, IOException {
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet request = new HttpGet(urlString);
//            HttpResponse response = httpClient.execute(request);
//            return response.getEntity().getContent();
//        }
//    }
//    
//    public class URLDrawable extends BitmapDrawable {
//        // the drawable that you need to set, you could set the initial drawing
//        // with the loading image if you need to
//        protected Drawable drawable;
//
//        @Override
//        public void draw(Canvas canvas) {
//            // override the draw to facilitate refresh function later
//            if(drawable != null) {
//                drawable.draw(canvas);
//            }
//        }
//    }
//    
//}
