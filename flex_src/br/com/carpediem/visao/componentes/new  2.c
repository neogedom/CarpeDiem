/*1. Escrever um algoritmo para ler o cadastro de 10 profissionais (c�digo, nome, data 

de nascimento (dia, m�s e ano), idade e altura). Uma vez cadastrados, este 

algoritmo devera, encontrar e imprimir o profissional mais idoso e o mais novo, o 

mais alto e o mais baixo e a data de nascimento deles. Usar tipos e t�cnicas de 

modulariza��o.*/


#include<string.h>
#include<conio.h>
#include<stdio.h>
#include <stdlib.h>

struct profissional {
int cod;
char nome[50];
int dataNascimento[10];
int idade;
float altura;
}

int
//ler funcion�rio
