let timerElem = document.getElementById("time")
let startBtn = document.getElementById("startBtn")
let pauseBtn = document.getElementById("pauseBtn")
let resetBtn = document.getElementById("resetBtn")

let tomato = {
    //25 min * 60 = 1500 secondi
    seconds:1500,
    running:false,
    timer:null
} // let tomato = {} <-- Creazione oggetto tomato

function showTimer(){
    //Converto da secs a mins
    let mins = Math.floor(tomato.seconds / 60)
    let secs = tomato.seconds % 60

    if(secs < 10){
        secs = "0" + secs
    }

    if(mins < 10){
        mins = "0" + mins
    }

    timerElem.textContent = mins + ":" + secs
}

function tick(){
    //-- => tomato.seconds = tomato.seconds - 1
    tomato.seconds--
    showTimer()
}

function start(){
    if(!tomato.running){
        //setInterval(cosaFare, ogniQuantoFarlo)
            //setTimeout(p1,p2) => esegue p1 tra p2 ONESHOT
            //setInterval => esegui paramentro 1 ogni paramentro 2 ms
        tomato.timer = setInterval(tick, 1000); 
        tomato.running = true;
    }
}

function pause(){
    //fa partire un timer di 5 minuti
    tomato.seconds = 5 * 60
    clearInterval(tomato.timer)
    showTimer();
}

function reset(){
    tomato.seconds = 1500;
    tomato.running = false;
    clearInterval(tomato.timer)
    showTimer();
}

pauseBtn.addEventListener("click", pause)
resetBtn.addEventListener("click", reset)
startBtn.addEventListener("click", start)
