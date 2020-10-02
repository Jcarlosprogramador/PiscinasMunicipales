package uem.dam.seg.piscinasmunicipales_juancarlos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Area {

        @SerializedName("@id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

