package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection();
            Scanner sc = new Scanner(System.in)) {

            MidiaDAO dao = new MidiaDAO(connection);

            int opcao;
            do {
                System.out.println("\n===== GERENCIAMENTO DE MÍDIAS =====");
                System.out.println("1 - Cadastrar mídia");
                System.out.println("2 - Buscar mídia por ID");
                System.out.println("3 - Listar todas as mídias");
                System.out.println("4 - Atualizar mídia");
                System.out.println("5 - Deletar mídia");
                System.out.println("6 - Total de mídias");
                System.out.println("7 - Listar por tipo");
                System.out.println("8 - Listar por franquia");
                System.out.println("9 - Quantidade por tipo");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                System.out.println("\n");
                sc.nextLine();

                switch (opcao) {
                    case 1: {
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();
                        System.out.print("Franquia: ");
                        String franquia = sc.nextLine();
                        System.out.print("Nota: ");
                        int nota = sc.nextInt();

                        Midia nova = new Midia(0, titulo, tipo, franquia, nota);
                        dao.criarMidia(nova);
                        System.out.println("Mídia cadastrada com ID: " + nova.getId());
                        break;
                    }
                    case 2: {
                        System.out.print("Digite o ID da mídia: ");
                        int id = sc.nextInt();
                        Midia m = dao.buscarId(id);
                        if (m != null) {
                            System.out.println(m);
                        } else {
                            System.out.println("Mídia não encontrada.");
                        }
                        break;
                    }
                    case 3: {
                        List<Midia> midias = dao.listarMidias();
                        midias.forEach(System.out::println);
                        break;
                    }
                    case 4: {
                        System.out.print("ID da mídia a atualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // limpar buffer
                        Midia existente = dao.buscarId(id);
                        if (existente != null) {
                            System.out.print("Novo título: ");
                            existente.setTitulo(sc.nextLine());
                            System.out.print("Novo tipo: ");
                            existente.setTipo(sc.nextLine());
                            System.out.print("Nova franquia: ");
                            existente.setFranquia(sc.nextLine());
                            System.out.print("Nova nota: ");
                            existente.setNota(sc.nextInt());

                            if (dao.atualizarMidia(existente)) {
                                System.out.println("Mídia atualizada com sucesso!");
                            } else {
                                System.out.println("Falha ao atualizar.");
                            }
                        } else {
                            System.out.println("Mídia não encontrada.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("ID da mídia a deletar: ");
                        int id = sc.nextInt();
                        if (dao.deletarMidia(id)) {
                            System.out.println("Mídia deletada!");
                        } else {
                            System.out.println("Mídia não encontrada.");
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("Total de mídias: " + dao.totalMidias());
                        break;
                    }
                    case 7: {
                        System.out.print("Digite o tipo: ");
                        String tipo = sc.nextLine();
                        List<Midia> midias = dao.listarPorTipo(tipo);
                        midias.forEach(System.out::println);
                        break;
                    }
                    case 8: {
                        System.out.print("Digite a franquia: ");
                        String franquia = sc.nextLine();
                        List<Midia> midias = dao.listarPorFranquia(franquia);
                        midias.forEach(System.out::println);
                        break;
                    }
                    case 9:
                        Map<String, Integer> midias = dao.midiasPorTipo();
                        midias.forEach((tipo, quantidade) -> System.out.println(tipo + ": " + quantidade));
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

            } while (opcao != 0);

        } catch (SQLException e) {
            System.err.println("Erro no banco: " + e.getMessage());
        }
    }
}
