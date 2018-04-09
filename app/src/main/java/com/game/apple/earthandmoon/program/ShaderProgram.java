package com.game.apple.earthandmoon.program;

import android.content.Context;

import com.game.apple.earthandmoon.util.ShaderHelper;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/19.
 */
public class ShaderProgram {
    protected int program;
    protected int cameraLocation;
    protected int lightLocation;
    protected int normalLocation;
    protected ShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        program = ShaderHelper.bulidProgram(context, vertexRawID, fragmentRawID);
    }


    public int getNormalLocation() {
        return normalLocation;
    }

    public void useProgram() {
        glUseProgram(program);
    }
}
