package br.com.unknown.games.repository

import br.com.unknown.games.model.Game
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepo : MongoRepository<Game, String> {
    fun findByNomeIgnoreCaseContaining(nome: String): List<Game>
    fun findByCategoriaIgnoreCaseAndAnoLancamento(categoria: String, anoLancamento: Integer): List<Game>
}