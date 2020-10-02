package uem.dam.seg.piscinasmunicipales_juancarlos.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Piscinas {

    @SerializedName("@context")
    @Expose
    private Contexto context;
    @SerializedName("@graph")
    @Expose
    private List<Graph> graph = null;

    public Contexto getContext() {
        return context;
    }

    public void setContext(Contexto context) {
        this.context = context;
    }

    public List<Graph> getGraph() {
        return graph;
    }

    public void setGraph(List<Graph> graph) {
        this.graph = graph;
    }
}
