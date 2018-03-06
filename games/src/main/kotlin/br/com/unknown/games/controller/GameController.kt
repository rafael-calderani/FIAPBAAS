package br.com.unknown.games.controller

import br.com.unknown.games.model.Game
import br.com.unknown.games.service.GameServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin // Enable cross domain requests
@RequestMapping(value = "/game")
class GameController { // Responsible for parsing the request and sending them to the Services
    @Autowired
    lateinit var gameServices: GameServices

    @GetMapping
    fun listAll() : List<Game>{
        return gameServices.listGames()
    }

    @GetMapping(value = "/titulo/{titulo}")
    fun listAllByName(@PathVariable(value = "titulo") titulo: String) : List<Game>{
        return gameServices.listGamesByNome(titulo)
    }

    @GetMapping(value = "/categoria/{categoria}/anolancamento/{anolancamento}")
    fun listAllByCategoriaAndAnoLancamento(
            @PathVariable(value = "categoria") categoria: String,
            @PathVariable(value = "anolancamento") anolancamento: Integer) : List<Game>{
        return gameServices.listGamesByCategoriaAndAnoLancamento(categoria, anolancamento)
    }

    @PostMapping
    fun save(@RequestBody game: Game){
        return gameServices.save(game)
    }

    @DeleteMapping(value = "{id}")
    fun delete(@PathVariable(value="id") id: String){
        gameServices.delete(id)
    }
}