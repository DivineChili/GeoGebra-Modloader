/*
This file is part of Renoir, a modern graphics library.

Copyright (c) 2012-2016 Coherent Labs AD and/or its licensors. All
rights reserved in all media.

The coded instructions, statements, computer programs, and/or related
material (collectively the "Data") in these files contain confidential
and unpublished information proprietary Coherent Labs and/or its
licensors, which is protected by United States of America federal
copyright law and by international treaties.

This software or source code is supplied under the terms of a license
agreement and nondisclosure agreement with Coherent Labs AD and may
not be copied, disclosed, or exploited except in accordance with the
terms of that agreement. The Data may not be disclosed or distributed to
third parties, in whole or in part, without the prior written consent of
Coherent Labs AD.

COHERENT LABS MAKES NO REPRESENTATION ABOUT THE SUITABILITY OF THIS
SOURCE CODE FOR ANY PURPOSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT
HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY, NONINFRINGEMENT, AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER, ITS AFFILIATES,
PARENT COMPANIES, LICENSORS, SUPPLIERS, OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OR PERFORMANCE OF THIS SOFTWARE OR SOURCE CODE,
EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
const char* CohCommonPS =
"layout(std140) uniform;        \n"
"                               \n"
"uniform sampler2D txBuffer;    \n"
"uniform sampler2D txBuffer1;   \n"
"uniform sampler2D txBuffer2;   \n"
"uniform sampler2D txBuffer3;   \n"
"                               \n"
"uniform GlobalDataPS{          \n"
"	vec2 ViewportSize;          \n"
"};                             \n"
"                               \n"
"uniform PerPrimitivePS{        \n"
"	int ShaderType;             \n"
"};                             \n"
"                               \n"
"uniform PrimitiveAdditionalPS{ \n"
"	vec4 PrimProps0;            \n"
"	vec4 PrimProps1;            \n"
"};                             \n"
"                               \n"
"uniform EffectPS{              \n"
"	vec4 Coefficients[3];       \n"
"	vec4 PixelOffsets[6];       \n"
"};                             \n"
"                               \n"
"uniform RenoirShaderParamsPS { \n"
"	vec4 GradientStartColor;    \n"
"	vec4 GradientMidColor;      \n"
"	vec4 GradientEndColor;      \n"
"	float GradientYCoord;       \n"
"};                             \n";
