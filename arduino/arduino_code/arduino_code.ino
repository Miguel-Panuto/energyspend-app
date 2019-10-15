#define CURRENTSENSOR A0
const float vpp = 0.0048828125;
const float sen = 0.066;
int calibragem;
float sensorValue, corrente, potencia;
void setup() {
 Serial.begin(9600);
 pinMode (CURRENTSENSOR, INPUT);
 calibragem = mediaSensor();
 Serial.println("PRONTO");
}
void loop() {
 int valueAux = abs(mediaSensor() - calibragem);
 sensorValue = valueAux * vpp;
 corrente = sensorValue / sen;
 Serial.println(corrente);
}

int mediaSensor() {
 float medVal = 0;
 int valueAux;
 for (int i = 0; i < 10000; i++) {
 medVal += analogRead(CURRENTSENSOR) ;
 }
 medVal = (medVal / 10000);
 valueAux = medVal;
 return medVal;
 }
