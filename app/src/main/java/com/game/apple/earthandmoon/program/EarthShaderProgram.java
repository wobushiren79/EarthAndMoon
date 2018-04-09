package com.game.apple.earthandmoon.program;

import android.content.Context;
import android.util.Log;

import java.nio.FloatBuffer;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/19.
 */
public class EarthShaderProgram extends ShaderProgram {
    private int positionLocation;
    private int matrixLocation;
    private int moveMatrixLocation;
    private int textureLocation;

    private int dayTextureLocation;
    private int nightTextureLocation;

    public EarthShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        super(context, vertexRawID, fragmentRawID);
        positionLocation = glGetAttribLocation(program, "Position");
        matrixLocation = glGetUniformLocation(program, "Matrix");
        moveMatrixLocation = glGetUniformLocation(program, "uMMatrix");
        cameraLocation = glGetUniformLocation(program, "uCamera");
        lightLocation = glGetUniformLocation(program, "uLightLocationSun");

        normalLocation = glGetAttribLocation(program, "aNormal");
        textureLocation = glGetAttribLocation(program, "Texture");
        dayTextureLocation = glGetUniformLocation(program, "TextureDataDay");
        nightTextureLocation = glGetUniformLocation(program, "TextureDataNight");
    }

    public void setUniform(float[] matrix, int daytexture, int nighttexture,FloatBuffer sunData,FloatBuffer cameraData) {
        glUniformMatrix4fv(matrixLocation, 1, false, matrix, 0);
        glUniformMatrix4fv(moveMatrixLocation, 1, false, matrix, 0);


        glUniform3fv(cameraLocation, 1, cameraData);
        glUniform3fv(lightLocation,1,sunData);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, daytexture);

        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, nighttexture);

        glUniform1i(dayTextureLocation, 0);
        glUniform1i(nightTextureLocation, 1);
    }

    public int getPositionLocation() {
        return positionLocation;
    }

    public int getTextureLocation() {
        return textureLocation;
    }
}
