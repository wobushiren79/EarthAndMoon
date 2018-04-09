package com.game.apple.earthandmoon.data;

import com.game.apple.earthandmoon.program.ShaderProgram;

/**
 * Created by apple on 2016/3/19.
 */
public class Camera {
    private VertexArrary vertexArrary;
    private float[] vertex ;

    public Camera(float[] vertex) {
        this.vertex=vertex;
        vertexArrary = new VertexArrary(vertex);
    }

    public VertexArrary getVertexArrary() {
        return vertexArrary;
    }
}
