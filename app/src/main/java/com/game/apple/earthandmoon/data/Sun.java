package com.game.apple.earthandmoon.data;

import com.game.apple.earthandmoon.program.ShaderProgram;
import com.game.apple.earthandmoon.program.SkyShaderProgram;

/**
 * Created by apple on 2016/3/19.
 */
public class Sun {
    private VertexArrary vertexArrary;
    private float[] vertex = {5f, 1f, 1f};

    public Sun() {
        vertexArrary = new VertexArrary(vertex);
    }

    public VertexArrary getVertexArrary() {
        return vertexArrary;
    }
}
