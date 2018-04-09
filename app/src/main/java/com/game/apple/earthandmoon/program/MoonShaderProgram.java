package com.game.apple.earthandmoon.program;

import android.content.Context;

import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniform3fv;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by apple on 2016/3/19.
 */
public class MoonShaderProgram extends ShaderProgram {
    private int positionLocation;
    private int matrixLocation;
    private int moveMatrixLocation;
    private int textureLocation;
    private int textureDataLocation;

    public MoonShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        super(context, vertexRawID, fragmentRawID);

        positionLocation = glGetAttribLocation(program, "Position");
        matrixLocation = glGetUniformLocation(program, "Matrix");
        moveMatrixLocation = glGetUniformLocation(program, "uMMatrix");
        cameraLocation = glGetUniformLocation(program, "uCamera");
        lightLocation = glGetUniformLocation(program, "uLightLocationSun");

        normalLocation = glGetAttribLocation(program, "aNormal");
        textureLocation = glGetAttribLocation(program, "Texture");

        textureDataLocation = glGetUniformLocation(program, "TextureData");
    }

    public int getPositionLocation() {
        return positionLocation;
    }

    public int getTextureLocation() {
        return textureLocation;
    }

    public void setUniform(float[] matrix,FloatBuffer sunvertex,FloatBuffer cameravertex,int texture) {
        glUniformMatrix4fv(matrixLocation, 1, false, matrix, 0);
        glUniformMatrix4fv(moveMatrixLocation, 1, false, matrix, 0);


        glUniform3fv(cameraLocation, 1, cameravertex);
        glUniform3fv(lightLocation, 1, sunvertex);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture);
        glUniform1i(textureDataLocation, 0);
    }
}
