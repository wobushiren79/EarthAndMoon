uniform mat4 Matrix;
uniform float PointSize;
attribute vec3 Position;
void main(){
gl_Position=Matrix*vec4(Position,1);
gl_PointSize=PointSize;
}