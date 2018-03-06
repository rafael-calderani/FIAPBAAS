package br.com.unknown.games.service

import br.com.unknown.games.model.Game
import br.com.unknown.games.repository.GameRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GameServices {
    @Autowired
    lateinit var gameRepo: GameRepo

    fun listGames() : List<Game> {
        return gameRepo.findAll()
    }

    fun listGamesByNome(nome : String) : List<Game> {
        return gameRepo.findByNomeIgnoreCaseContaining(nome)
    }

    fun listGamesByCategoriaAndAnoLancamento(categoria : String, anoLancamento: Integer) : List<Game> {
        return gameRepo.findByCategoriaIgnoreCaseContainingAndAnoLancamento(categoria, anoLancamento)
    }

    fun save(game: Game){
        // TODO: tratamento de erro
        gameRepo.save(game)
    }

    fun delete(id: String) {
        gameRepo.deleteById(id)
    }
}