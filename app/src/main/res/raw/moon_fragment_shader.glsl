precision mediump float;
varying vec2 TextureVary;
varying vec4 vAmbient;
varying vec4 vDiffuse;
varying vec4 vSpecular;
uniform sampler2D TextureData;
void main(){
vec4  finalColor=texture2D(TextureData,TextureVary);
gl_FragColor=finalColor*vAmbient+finalColor*vDiffuse+finalColor*vSpecular;
}