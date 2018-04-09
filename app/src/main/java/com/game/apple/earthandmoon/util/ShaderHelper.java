package com.game.apple.earthandmoon.util;

import android.content.Context;
import android.util.Log;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/19.
 */
public class ShaderHelper {
    public static int getVertexID(String code) {
        return complieShader(GL_VERTEX_SHADER, code);
    }

    public static int getfragmentID(String code) {
        return complieShader(GL_FRAGMENT_SHADER, code);
    }

    private static int complieShader(int type, String code) {
        int shaderID=glCreateShader(type);
        glShaderSource(shaderID,code);
        glCompileShader(shaderID);
        int[] complieStates=new int[1];
        glGetShaderiv(shaderID,GL_COMPILE_STATUS,complieStates,0);
        if(complieStates[0]==0){
            Log.v("this","编译着色器代码失败");
            Log.v("this",glGetShaderInfoLog(shaderID));
            glDeleteShader(shaderID);
            return 0;
        }
        return shaderID;
    }

    public static int bulidProgram(Context context,int vertexRawID,int fragmentRawID){

        String vertexCode=ReaderHelper.readRaw(context, vertexRawID);
        String fragmentCode=ReaderHelper.readRaw(context, fragmentRawID);

        int vertexID=getVertexID(vertexCode);
        int fragmentID=getfragmentID(fragmentCode);

        int program=glCreateProgram();
        glAttachShader(program, vertexID);
        glAttachShader(program, fragmentID);
        glLinkProgram(program);
        int[] linkStates=new int[1];
        glGetProgramiv(program,GL_LINK_STATUS,linkStates,0);
        if(linkStates[0]==0){
            Log.v("this","连接着色器失败");
            Log.v("this",glGetProgramInfoLog(program));
            glDeleteProgram(program);
            return 0;
        }
        return program;
    }
}
