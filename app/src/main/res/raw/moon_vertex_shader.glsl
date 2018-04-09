uniform mat4 Matrix;     //位移矩阵
uniform mat4 uMMatrix;    //变换矩阵
uniform vec3 uCamera;     //摄像机位置（用于镜面反射）
uniform vec3 uLightLocationSun; //光位置
attribute vec3 Position;       //点坐标
attribute vec2 Texture;        //纹理坐标
attribute vec3 aNormal;        //光入射的法向量坐标
varying vec2 TextureVary;      //纹理信息
varying vec4 vAmbient;           //环境光信息
varying vec4 vDiffuse;           //漫反射信息
varying vec4 vSpecular;          //镜面反射信息

void pointLight(            //光计算
in vec3 normal,
inout vec4 ambient,
inout vec4 diffuse,
inout vec4 specular,
in vec3 lightLocation,
in vec4 lightAmbient,
in vec4 lightDiffuse,
in vec4 lightSpecular){
  ambient=lightAmbient;
  vec3 normalTarget=Position+normal;
  vec3 newNormal=(uMMatrix*vec4(normalTarget,1)).xyz-(uMMatrix*vec4(Position,1)).xyz;
  newNormal=normalize(newNormal);
  vec3 eye=normalize(uCamera-(uMMatrix*vec4(Position,1)).xyz);


  vec3 vp=normalize(lightLocation-(uMMatrix*vec4(Position,1)).xyz);
  vp=normalize(vp);
  vec3 halfVector=normalize(vp+eye);
  float shininess=50.0;
  float nDotViewPosition=max(0.0,dot(newNormal,vp));
  diffuse=lightDiffuse*nDotViewPosition;
  float nDotViewHalfVector=dot(newNormal,halfVector);

  float powerFactor=max(0.0,pow(nDotViewHalfVector,shininess));
  specular=lightSpecular*powerFactor;
}
void main(){
gl_Position=Matrix*vec4(Position,1);
vec4 ambientTemp=vec4(0.0,0.0,0.0,0.0);     //初始化环境光
vec4 diffuseTemp=vec4(0.0,0.0,0.0,0.0);     //初始化漫反射
vec4 specularTemp=vec4(0.0,0.0,0.0,0.0);    //初始化镜面光

pointLight(normalize(aNormal),ambientTemp,diffuseTemp,specularTemp,uLightLocationSun,vec4(0.05,0.05,0.025,1.0),vec4(1.0,1.0,0.5,1.0),vec4(0.3,0.3,0.15,1.0));
vAmbient=ambientTemp;
vDiffuse=diffuseTemp;
vSpecular=specularTemp;
TextureVary=Texture;
}