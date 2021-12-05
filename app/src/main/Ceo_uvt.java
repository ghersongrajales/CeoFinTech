package com.ceofintech;

import com.google.firebase.database.IgnoreExtraProperties;

    // [START post_class]
    @IgnoreExtraProperties
    public class Ceo_uvt {
        public String fecha;
        public String periodo;
        public String resolu;
        public Number tasa;

        public Ceo_uvt() {
            // Default constructor required for calls to DataSnapshot.getValue(Post.class)
        }

        public Ceo_uvt(String fecha, String periodo, String resolu, Number tasa ) {
            this.fecha = fecha;
            this.periodo = periodo;
            this.resolu = resolu;
            this.tasa = tasa;
        }

    }
// [END post_class]
