#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_time;

uniform sampler2D u_texture;

uniform float frequency;
uniform float noiseFactor;
uniform float time;
uniform float intensity;
uniform float lineSpeed;
uniform vec3 ambient;
uniform float width;
uniform float height;

float rand(vec2 co, float time){
    return fract(sin(dot(co.xy ,vec2(12.9898 + time / 10.0,78.233))) * 43758.5453);
}

//RADIUS of our vignette, where 0.5 results in a circle fitting the screen
const float RADIUS = 1.0f;

//softness of our vignette, between 0.0 and 1.0
const float SOFTNESS = 0.95;

void main() {
 	//sample our texture
    vec4 texColor = texture2D(u_texture, v_texCoords);
	float speedFactor = 1.0 / lineSpeed * 10.0;
	float global_pos = (v_texCoords.y + time / speedFactor + 1000.0) * frequency;
    float wave_pos = cos((fract( global_pos ) - 0.5)*intensity);
    vec4 pel = texture2D( u_texture, v_texCoords );
    
    vec4 colorNoise = mix(pel, pel * rand(v_texCoords, time),noiseFactor);
    vec4 color = mix(colorNoise / 1.5, colorNoise, wave_pos + 0.5);
    
    vec2 resolution = vec2(width, height);
    
    //determine center position
    vec2 position = (gl_FragCoord.xy / resolution.xy) - vec2(0.5);

    //determine the vector length of the center position
    float len = length(position);

    //use smoothstep to create a smooth vignette
    float vignette = smoothstep(RADIUS, RADIUS-SOFTNESS, len);

    //apply the vignette with 50% opacity
    texColor.rgb = mix(texColor.rgb, texColor.rgb * vignette, 0.5);
    
     //2. GRAYSCALE

    //convert to grayscale using NTSC conversion weights
    float gray = dot(texColor.rgb, vec3(0.299, 0.287, 0.114));

    //3. SEPIA

    //create our sepia tone from some constant value
    vec3 sepiaColor = vec3(gray) * ambient;

    //again we'll use mix so that the sepia effect is at 75%
    texColor.rgb = mix(texColor.rgb, sepiaColor, 0.95);
    
    vec4 texelColor = texture2D(u_texture, gl_TexCoord[0].xy);
    vec4 scaledColor = texelColor * vec4(1.1, 1.1, 1.1, 1.0);
    float luminance = scaledColor.r + scaledColor.g + scaledColor.b ;
    vec4 col = vec4( luminance, luminance, luminance, texelColor.a);
    
    gl_FragColor = color * texColor * col;
}