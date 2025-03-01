
//UN UNICO OSTACOLO FISSO IN MEZZO GIGANTE

const config = {
    floor: {
        size: { x: 25, y: 20 }
    },
    player: {
        //position: { x: 0.5, y: 0.5 },		//CENTER
        position: { x: 0.14, y: 0.15 },		//INIT
        //position: { x: 0.8, y: 0.85 },		//END
        speed: 0.2
    },
    sonars: [
        {
            name: "sonar1",
            position: { x: 0.12, y: 0.05 },
            senseAxis: { x: false, y: true }
        },
        {
            name: "sonar2",
            position: { x: 0.94, y: 0.88},
            senseAxis: { x: true, y: false }
        } 
     ],
    movingObstacles: [
//        {
//            name: "moving-obstacle-1",
//            position: { x: .5, y: .4 },
//            directionAxis: { x: true, y: false },
//            speed: 1,
//            range: 4
//        },
//        {
//            name: "moving-obstacle-2",
//            position: { x: .5, y: .2 },
//            directionAxis: { x: true, y: true },
//            speed: 2,
//            range: 2
//        }
    ],
    staticObstacles: [
       {
           name: "obs1",
           centerPosition: { x: 0.5, y: 0.5},
           size: { x: 0.3, y: 0.3}
       },
		// {
           // name: "wall",
           // centerPosition: { x: 0.07, y: 0.7},
           // size: { x: 0.05, y: 0.05}
		// },
        {
        name: "wallUp",
        centerPosition: { x: 0.58, y: 0.98},
        size: { x: 0.8, y: 0.01}
        },
         {
            name: "wallDown",
            centerPosition: { x: 0.45, y: 0.01},
            size: { x: 0.85, y: 0.01}
        },
       {
            name: "wallRight",
            centerPosition: { x: 0.05, y: 0.4},
            size: { x: 0.01, y: 0.6}
        },
        {
            name: "wallLeft",
            centerPosition: { x: 0.9, y: 0.5},
            size: { x: 0.01, y: 0.65}
        }
    ]
}

export default config;