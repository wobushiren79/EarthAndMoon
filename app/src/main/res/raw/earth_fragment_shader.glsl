precision mediump float;
varying vec2 TextureVary;
varying vec4 vAmbient;
varying vec4 vDiffuse;
varying vec4 vSpecular;
uniform sampler2D TextureDataDay;
uniform sampler2D TextureDataNight;

void main(){
vec4 finalColorDay;
vec4 finalColorNight;
finalColorDay=texture2D(TextureDataDay,TextureVary);
finalColorDay=finalColorDay*vAmbient+finalColorDay*vSpecular+finalColorDay*vDiffuse;

finalColorNight=texture2D(TextureDataNight,TextureVary);
finalColorNight=finalColorNight*vec4(0.5,0.5,0.5,1.0);

if(vDiffuse.x>0.21){
gl_FragColor=finalColorDay;
}else if(vDiffuse.x<0.05){
gl_FragColor=finalColorNight;
}else {
float t=(vDiffuse.x-0.05)/0.16;
gl_FragColor=t*finalColorDay+(1.0-t)*finalColorNight;
}
}