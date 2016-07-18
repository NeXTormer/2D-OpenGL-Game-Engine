#version 330 core

in vec3 position;
in vec2 tc;

out vec3 color;
out vec2 tc_out;

void main() {
    gl_Position = vec4(position.xyz, 1.0);
    color = vec3(position.x + 0.5, 1, position.y + 0.5);
    tc_out = tc;
}