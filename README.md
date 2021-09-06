# Elevator-Service

Payload

1) To start process
url: http://localhost:8080/ElevatorSystem/startProc
method:GET
Response:{"msg": "Process is already started...","status": true}

2)To get status of elevator
url: http://localhost:8080/ElevatorSystem/status
method: GET
Response: {"movingState": "idle", "currFloor": 0}

3)Call Elevator from floor
url: http://localhost:8080/ElevatorSystem/callElevator
method: POST
Request: {"passengerFloor":4,"btnType":"floorBtn"}
Response: {"msg": "Door opening at floor :: 4","status": true} 

4)Call elevator from elevator cabbin
url: http://localhost:8080/ElevatorSystem/callElevator
method: POST
Request: {"passengerFloor":10,"btnType":"elevatorBtn"}
Response: {"msg": "Your destination at floor :: 10","status": true}