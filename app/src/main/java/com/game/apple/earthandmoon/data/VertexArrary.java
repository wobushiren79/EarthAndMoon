package com.game.apple.earthandmoon.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import static android.opengl.GLES20.*;
/**
 * Created by apple on 2016/3/19.
 */
public class VertexArrary {
    private FloatBuffer vertexData;

    public VertexArrary(float[] data) {
        vertexData= ByteBuffer
                .allocateDirect(data.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(data);
        vertexData.position(0);
    }

    public FloatBuffer getVertexData() {
        return vertexData;
    }

    public void setData(int dataoff,int location,int size,int stride){
        vertexData.position(dataoff);
        glVertexAttribPointer(location,size,GL_FLOAT,false,stride,vertexData);
        glEnableVertexAttribArray(location);
    }
}
