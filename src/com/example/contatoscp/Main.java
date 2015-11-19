package com.example.contatoscp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		ArrayList<String> agenda = new ArrayList();
		Scanner ler = new Scanner(System.in);

		int opcao;

		importar(agenda);

		do {
			System.out.printf("**Menu Principal**\n");
			System.out.printf("[1] Incluir Contato\n");
			System.out.printf("[2] Excluir Contato\n ");
			System.out.printf("[3] Listar Contatos\n");
			System.out.printf("[4] Pesquisar Contato\n");
			System.out.printf("[0] Encerrar o Porgrama\n");
			System.out.printf("\n Opção Deseejada: ");

			opcao = ler.nextInt();

			switch (opcao) {

			case 1:
				incluir(agenda);
				break;

			case 2:
				excluir(agenda);
				break;

			case 3:
				listar(agenda);
				break;

			case 4:
				pesquisar(agenda);
				break;
			}

			System.out.printf("\n\n");

		} while (opcao != 0);

		exportar(agenda);
	}

	private static void importar(ArrayList<String> agenda) {

		try {

			FileReader arq = new FileReader("agenda.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();// le a primeira linha

			// a variavel "Linha" recebe o valor "null" quando o processo
			// de repetiçao atingir o final do arquivo texto

			while (linha != null) {
				agenda.add(linha);
				linha = lerArq.readLine(); // le da segunda até a ultima linha

			}

			arq.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo : %s.",
					e.getMessage());
		}

	}

	private static void exportar(ArrayList<String> agenda) throws IOException {

		FileWriter arq = new FileWriter("agenda.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		int i, n = agenda.size();
		for (i = 0; i < n; i++) {

			gravarArq.printf("%s%n", agenda.get(i));
		}

		gravarArq.close();
	}

	private static void incluir(ArrayList<String> agenda) {

		Scanner ler = new Scanner(System.in);
		String nome, telefone;

		System.out.printf("\n Informe o nome do contato: \n");
		nome = ler.nextLine();

		System.out.printf("\n Informe o telefone do contato: \n");
		telefone = ler.nextLine();

		// gravar os dados no final da lista
		agenda.add(nome + ";" + telefone);

	}

	private static void excluir(ArrayList<String> agenda) {
		Scanner ler = new Scanner(System.in);

		int i;

		listar(agenda);

		System.out.printf("\n Informe a posiçao a ser excluída: \n");
		i = ler.nextInt();

		try {

			agenda.remove(i);

		} catch (IndexOutOfBoundsException e) {
			// a exeçao laçada para indicar que um indice (i)
			// esta fora do intervalo valido

			System.out.printf("\n Erro : Posiçao invalida (%s). \n\n",
					e.getMessage());

		}

	}

	private static void listar(ArrayList<String> agenda) {

		System.out.printf("\n Listando os itens da Agenda: \n");

		int i, n = agenda.size();

		for (i = 0; i < n; i++) {

			System.out.printf("Posição %d- %s\n", i, agenda.get(i));
		}

		System.out.printf("=========================================");

	}

	private static void pesquisar(ArrayList<String> agenda) {

		Scanner ler = new Scanner(System.in);
		String s;

		System.out.printf("\n Informe o nome do contato : \n");
		s = ler.nextLine();

		int i, n = agenda.size();

		s = s.toUpperCase();
		String dados[];

		for (i = 0; i < n; i++) {
			// Lista de pessoas com o nome informado

			if (agenda.get(i).toUpperCase().indexOf(s) != -1) {

				dados = agenda.get(i).split(";");
				System.out.printf("\n Nome : %s", dados[0]);
				System.out.printf("\n Telefone: %s\n", dados[1]);

			}

		}
	}

}
