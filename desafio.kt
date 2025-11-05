// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario (val nome: String, val id: Int)

//Adicionando o nivel do conteúdo 
data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel = Nivel.BASICO)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()
    
    //Tempo de duração total da formação
    val duracaoTotal: Int
        get() = conteudos.sumOf { it.duracao }
    
    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)){
            println("O Aluno ${usuario.nome} já está matriculado em $nome.")
        } else {
           inscritos.add(usuario)
            println("${usuario.nome} matriculado com sucesso em $nome.")
        }
    }
}

fun main() {
    //Enviando sem o nível
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 120)
    //Enviando sem o tempo
    val conteudo2 = ConteudoEducacional(nome = "Orientação a Objetos",nivel = Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Programação Funcional", 240, Nivel.AVANCADO)
    val conteudo4 = ConteudoEducacional("Bancos de Dados SQL", 100, Nivel.INTERMEDIARIO)
    
    val formacaoBackend = Formacao(
        nome = "Formação Desenvolvedor Backend Kotlin",
        conteudos = listOf(conteudo1, conteudo2, conteudo4),
        nivel = Nivel.INTERMEDIARIO
    )

    val formacaoFuncional = Formacao(
        nome = "Formação Programação Funcional com Kotlin",
        conteudos = listOf(conteudo3, conteudo2),
        nivel = Nivel.AVANCADO
    )
    
    val alunoA = Usuario("João Silva", 1001)
    val alunoB = Usuario("Maria Souza", 1002)
    val alunoC = Usuario("Pedro Costa", 1003)

    println("--- SIMULAÇÃO DE MATRÍCULAS ---")
    
    formacaoBackend.matricular(alunoA)
    formacaoBackend.matricular(alunoB)
    formacaoFuncional.matricular(alunoB)
    formacaoFuncional.matricular(alunoC)
    
    // Tentativa de matrícula duplicada
    formacaoBackend.matricular(alunoA)

    println("\n--- RELATÓRIO DAS FORMAÇÕES ---")
    
    fun imprimirRelatorio(formacao: Formacao) {
        println("==================================================")
        println("Formação: ${formacao.nome}")
        println("Nível: ${formacao.nivel}")
        println("Duração Total: ${formacao.duracaoTotal} minutos")
        println("Conteúdos (${formacao.conteudos.size}):")
        formacao.conteudos.forEach { conteudo ->
            println("   - ${conteudo.nome} (${conteudo.duracao}min, Nível: ${conteudo.nivel})")
        }
        println("Total de Inscritos: ${formacao.inscritos.size}")
        print("Alunos: ")
        if (formacao.inscritos.isEmpty()) {
            println("Nenhum")
        } else {
            println(formacao.inscritos.joinToString(", ") { it.nome })
        }
        println("==================================================")
    }

    imprimirRelatorio(formacaoBackend)
    imprimirRelatorio(formacaoFuncional)
}
