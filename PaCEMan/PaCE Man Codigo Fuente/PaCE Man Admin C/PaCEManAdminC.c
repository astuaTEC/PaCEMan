#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include "Constants.c"


/*
Estructura utilizada para almacenar la posicion en el eje x y la posicion en el eje y, segun corresponda el mensaje
proveniente de Java. Estos valores se rellenan en funcion de la longitud de dicho mensaje, al tener que acceder a
diferentes posiciones dependiendo del tipo del mismo.
*/
struct Position{
    int xPosition;
    int yPosition;
};

struct AnswerToJava{
    char messageID;
    bool hasExtraInfo;
    int extraInfo;
};


/*
Funcion utilizada para revisar si la posicion colocada por el administrador en la interfaz de Java es una posicion
valida dentro de la matriz. Se vale del acceso a la constante SERVER_MATRIX, en una posicion xy dada por la estructura
Position. Si el valor encontrado en la matriz es un 0, la posicion no es valida. Si el valor es un 1, la posicion
es valida. Recibe como parametro a la posicion actual en forma (x,y) de tipo char*.
 */
bool checkAvailablePosition(char* currentPosition){
    struct Position position;
    if(strlen(currentPosition)==5){
        position.xPosition = currentPosition[1]-'0';
        position.yPosition = currentPosition[3]-'0';
    }else if(strlen(currentPosition)==7){
        position.xPosition = ((currentPosition[1]-'0')*10)+(currentPosition[2]-'0');
        position.yPosition = ((currentPosition[4]-'0')*10)+(currentPosition[5]-'0');
    }else if(strlen(currentPosition)==6){
        if(currentPosition[2]==',') {
            position.xPosition = currentPosition[1] - '0';
            position.yPosition = ((currentPosition[3] - '0') * 10) + (currentPosition[4] - '0');
        }else{
            position.xPosition = ((currentPosition[1]-'0')*10)+(currentPosition[2]-'0');
            position.yPosition = currentPosition[4]-'0';
        }
    }
    if(SERVER_MATRIX[position.xPosition][position.yPosition]==1){
        return true;
    }

    struct AnswerToJava message;
    message.hasExtraInfo=false;
    message.extraInfo=123;
    message.messageID='2';

    printf(message.extraInfo);
    printf(message.hasExtraInfo);
    printf(message.messageID);

    return false;
}


/*
Funcion encargada de leer la base de datos que contiene la informacion de la partida actual. Este codigo en C modifica
esta base de datos por cada llamada que recibe del administrador en Java, y en base a estos datos decide si los mensajes
son validos o si debe aumentar una vida, quitar un fantasma, cambiar de nivel, entre otros. Recibe como parametro el
numero de la posicion dentro de la base de datos del dato al que se puede acceder. Estos numeros se almacenan en el
archivo de constantes, por ejemplo, POINTS_DB_POSITION = 5. Recorre el txt y retorna la linea especificada por el numero
del parametro.
*/
char* readDataBase(int requiredLine){
    requiredLine = 2 * requiredLine;
    FILE* dataBase;
    fopen_s(&dataBase, DATA_BASE_PATH, "r");
    char* requiredData = malloc(DATA_LENGHT);
    while (fgets(requiredData, DATA_LENGHT, dataBase) != NULL)  {
        if(requiredLine==1){
            fclose(dataBase);
            return requiredData;
        }
        requiredLine--;
    }
    free(requiredData);
    fclose(dataBase);
    return requiredData;
}


/*
Funcion que se encarga de actualizar la base de datos. Remueve la base de datos anterior, y por medio de su parametro
newDataBaseData, rellena la nueva base de datos con cada uno de los indices de dicho parametro. Cada indice hace
referencia al nuevo valor de los datos a almacenar.
*/
void updateDataBase(int newDataBaseData[8]){
    remove(DATA_BASE_PATH);
    FILE* fr;
    fopen_s(&fr,DATA_BASE_PATH, "w");
    fprintf(fr,"1. isPinkyActive:\n");
    fprintf(fr,"%d\n",newDataBaseData[0]);
    fprintf(fr,"2. isInkyActive:\n");
    fprintf(fr,"%d\n",newDataBaseData[1]);
    fprintf(fr,"3. isBlinkyActive:\n");
    fprintf(fr,"%d\n",newDataBaseData[2]);
    fprintf(fr,"4. isClydeActive:\n");
    fprintf(fr,"%d\n",newDataBaseData[3]);
    fprintf(fr,"5. currentPoints:\n");
    fprintf(fr,"%d\n",newDataBaseData[4]);
    fprintf(fr,"6. currentLives:\n");
    fprintf(fr,"%d\n",newDataBaseData[5]);
    fprintf(fr,"7. currentLevel:\n");
    fprintf(fr,"%d\n",newDataBaseData[6]);
    fprintf(fr,"8. currentSpeed:\n");
    fprintf(fr,"%d\n",newDataBaseData[7]);
    fprintf(fr,"END");
    fclose(fr);
}


/*
Funcion que se encarga de modificar el valor de uno de los fantasmas, pudiendo pasarlo de activo(1) a inactivo(0) segun
sea su estado actual (lo cambia al estado contrario). Recibe como parametro un ghostID, que son valores dentro del archivo
de constantes. Por ejemplo, PINKY_ID = 1. Esta funcion recorre la base de datos, rellenando
 */
void changeGhostStatus(int ghostID){
    FILE* dataBase;
    fopen_s(&dataBase, DATA_BASE_PATH, "r");
    char* data = malloc(DATA_LENGHT);
    int newDataBaseData[8];
    int dataNumber=2;
    int newDataBaseDataIndex=0;
    while (fgets(data, DATA_LENGHT, dataBase) != NULL)  {
        if(dataNumber%2!=0){
            if(ghostID==1){
                if(atoi(data)==0){
                    newDataBaseData[newDataBaseDataIndex]=1;
                }else{
                    newDataBaseData[newDataBaseDataIndex]=0;
                }
            }else{
                newDataBaseData[newDataBaseDataIndex]=atoi(data);
            }
            ghostID--;
            newDataBaseDataIndex++;
        }
        dataNumber++;
    }
    fclose(dataBase);
    free(data);
    updateDataBase(newDataBaseData);
}


/*
Funcion que se encarga de modificar el valor de la velocidad. Segun el parametro ID, las constantes dentro del archivo
de constantes pueden indicar si se trata de un caso de aumento de velocidad (2) o de disminucion de velocidad (1). En caso
de tratarse de un cambio de nivel, tambien existen los identificadores para colocar la velocidad por defecto del segundo nivel
(3) y del tercer nivel (4).
 */
void changeSpeed(int ID){
    FILE* dataBase;
    fopen_s(&dataBase, DATA_BASE_PATH, "r");
    char* data = malloc(DATA_LENGHT);
    int newDataBaseData[8];
    int dataNumber=2;
    int newDataBaseDataIndex=0;
    int currentSpeed = 0;
    while (fgets(data, DATA_LENGHT, dataBase) != NULL)  {
        if(dataNumber%2!=0){
            newDataBaseData[newDataBaseDataIndex]=atoi(data);
            newDataBaseDataIndex++;
        }
        if(dataNumber==17){
            currentSpeed=atoi(data);
        }
        dataNumber++;
    }
    if(ID==INCREASE_ID){
        newDataBaseData[7]=currentSpeed+1;
    }else if(ID==DECREASE_ID){
        newDataBaseData[7]=currentSpeed-1;
    }else if(ID==DEFAULT_LEVEL2_ID){
        newDataBaseData[7]=2;
    }else{
        newDataBaseData[7]=3;
    }
    fclose(dataBase);
    free(data);
    updateDataBase(newDataBaseData);
}


/*
Funcion que se encaga de modificar el valor de los puntos. Si el identificador es 1, quiere decir que se deben reiniciar
los puntos porque se ha cambiado de nivel. En caso de que el identificador sea 0, quiere decir que se deben aumentar
los puntos en 20 unidades, ya que el jugador a agarrado un PacDot.
*/
void changePoints(int ID){
    FILE* dataBase;
    fopen_s(&dataBase, DATA_BASE_PATH, "r");
    char* data = malloc(DATA_LENGHT);
    int newDataBaseData[8];
    int dataNumber=2;
    int newDataBaseDataIndex=0;
    int currentPoints = 0;
    while (fgets(data, DATA_LENGHT, dataBase) != NULL)  {
        if(dataNumber%2!=0){
            newDataBaseData[newDataBaseDataIndex]=atoi(data);
            newDataBaseDataIndex++;
        }
        if(dataNumber==11){
            currentPoints=atoi(data);
        }
        dataNumber++;
    }
    if(ID==RESTART_POINTS){
        newDataBaseData[4]=0;
    }else{
        newDataBaseData[4]=currentPoints+POINT_VALUE;
    }
    fclose(dataBase);
    free(data);
    updateDataBase(newDataBaseData);
}


/*
Funcion que se encarga de cambiar el valor de las vidas. En caso de que el parametro ID sea WIN_LIVE_ID = 1 (dentro del
archivo de constantes), se debe aumentar en 1 el valor de las vidas. Caso contrario, se debe disminuir en 1 el valor de las
mismas.
*/
void changeLives(int ID){
    FILE* dataBase;
    fopen_s(&dataBase, DATA_BASE_PATH, "r");
    char* line = malloc(DATA_LENGHT);
    int newDataBaseData[8];
    int dataNumber=2;
    int newDataBaseDataIndex=0;
    int currentLives=0;
    while (fgets(line, DATA_LENGHT, dataBase) != NULL)  {
        if(dataNumber%2!=0){
            newDataBaseData[newDataBaseDataIndex]=atoi(line);
            newDataBaseDataIndex++;
        }
        if(dataNumber==13){
            currentLives=atoi(line);
        }
        dataNumber++;
    }
    fclose(dataBase);
    free(line);

    if(ID==WIN_LIVE_ID){
        newDataBaseData[5]=currentLives+1;
    }else{
        newDataBaseData[5]=currentLives-1;
    }
    updateDataBase(newDataBaseData);
}


/*
Funcion encargada de responderle a Java, para comunicarle la respuesta al cliente.
*/
void createJavaClientMessage(int messageID){
    if(messageID==UPDATE_POINTS){
        printf("0,%s",readDataBase(POINTS_DB_POSITION));
    }else if(messageID==VALID_POSITION){
        printf("1");
    }else if(messageID==INVALID_POSITION){
        printf("2");
    }else if(messageID==CAN_SPAWN_PINKY){
        printf("3");
    }else if(messageID==CANT_SPAWN_PINKY){
        printf("4");
    }else if(messageID==CAN_SPAWN_INKY){
        printf("5");
    }else if(messageID==CANT_SPAWN_INKY){
        printf("6");
    }else if(messageID==CAN_SPAWN_BLINKY){
        printf("7");
    }else if(messageID==CANT_SPAWN_BLINKY){
        printf("8");
    }else if(messageID==CAN_SPAWN_CLYDE){
        printf("a,%s",readDataBase(SPEED_DB_POSITION));
    }else if(messageID==CANT_SPAWN_CLYDE){
        printf("b");
    }else if(messageID==CAN_SPAWN_FRUIT){
        printf("c");
    }else if(messageID==CAN_SPAWN_POWER_PILL){
        printf("d");
    }else if(messageID==CAN_INCREASE_SPEED){
        printf("e,%s",readDataBase(SPEED_DB_POSITION));
    }else if(messageID==CANT_INCREASE_SPEED){
        printf("f");
    }else if(messageID==CAN_DECREASE_SPEED){
        printf("g,%s",readDataBase(SPEED_DB_POSITION));
    }else if(messageID==CANT_DECREASE_SPEED){
        printf("h");
    }else if(messageID==WIN_LIVE){
        printf("i,%s",readDataBase(LIVES_DB_POSITION));
    }else if(messageID==LOSE_LIVE){
        printf("j,%s",readDataBase(LIVES_DB_POSITION));
    }
}

/*
Funcion main del codigo de C. Se encarga de recibir un mensaje por parte de Java, y por medio de los ID que
se encuentran en el archivo de constantes, se determina que tipo de mensaje debe ser interpretado y respondido.
*/
int main(int argc, char** argv) {
    char* messageID = argv[1];
    char* currentPosition = argv[2];
    if(strcmp(messageID,NEW_POSITION)==0){
        if(checkAvailablePosition(currentPosition)==true){
            createJavaClientMessage(VALID_POSITION);
        }else{
            createJavaClientMessage(INVALID_POSITION);
        }


    }else if(strcmp(messageID,SPAWN_PINKY)==0){
        if(strcmp(readDataBase(PINKY_DB_POSITION),"0\n")==0){
            changeGhostStatus(PINKY_ID);
            createJavaClientMessage(CAN_SPAWN_PINKY);
        }else{
            createJavaClientMessage(CANT_SPAWN_PINKY);
        }


    }else if(strcmp(messageID,SPAWN_INKY)==0){
        if(strcmp(readDataBase(INKY_DB_POSITION),"0\n")==0){
            changeGhostStatus(INKY_ID);
            createJavaClientMessage(CAN_SPAWN_INKY);
        }else{
            createJavaClientMessage(CANT_SPAWN_INKY);
        }
    }else if(strcmp(messageID,SPAWN_BLINKY)==0){
        if(strcmp(readDataBase(BLINKY_DB_POSITION),"0\n")==0){
            changeGhostStatus(BLINKY_ID);
            createJavaClientMessage(CAN_SPAWN_BLINKY);
        }else{
            createJavaClientMessage(CANT_SPAWN_BLINKY);
        }
    }else if(strcmp(messageID,SPAWN_CLYDE)==0){
        if(strcmp(readDataBase(CLYDE_DB_POSITION),"0\n")==0){
            changeGhostStatus(CLYDE_ID);
            createJavaClientMessage(CAN_SPAWN_CLYDE);
        }else{
            createJavaClientMessage(CANT_SPAWN_CLYDE);
        }
    }else if(strcmp(messageID,SPAWN_FRUIT)==0){
        createJavaClientMessage(CAN_SPAWN_FRUIT);
    }else if(strcmp(messageID,SPAWN_POWER_PILL)==0){
        createJavaClientMessage(CAN_SPAWN_POWER_PILL);
    }else if(strcmp(messageID,INCREASE_SPEED)==0){
        if(atoi(readDataBase(SPEED_DB_POSITION))<5){
            changeSpeed(INCREASE_ID);
            createJavaClientMessage(CAN_INCREASE_SPEED);
        }else{
            createJavaClientMessage(CANT_INCREASE_SPEED);
        }
    }else if(strcmp(messageID,DECREASE_SPEED)==0){
        if(atoi(readDataBase(SPEED_DB_POSITION))>1){
            changeSpeed(DECREASE_ID);
            createJavaClientMessage(CAN_DECREASE_SPEED);
        }else{
            createJavaClientMessage(CANT_DECREASE_SPEED);
        }
    }
    else if(strcmp(messageID,INCREASE_POINTS)==0){
        if(atoi(readDataBase(POINTS_DB_POSITION))+POINT_VALUE==POINTS_TO_WIN_LIVE){
            changePoints(0);
            changeLives(WIN_LIVE_ID);
            createJavaClientMessage(WIN_LIVE);
        }else{
            changePoints(0);
            createJavaClientMessage(UPDATE_POINTS);
        }
    }
    else if(strcmp(messageID,INCREASE_LEVEL)==0){
        if(atoi(readDataBase(LEVEL_DB_POSITION))==1){
            changeSpeed(DEFAULT_LEVEL2_ID);
            changeLives(RESTART_POINTS);
        }else{
            changeLives(RESTART_POINTS);
            changeSpeed(DEFAULT_LEVEL3_ID);
        }
    }
    else if(strcmp(messageID,HIT_BY_GHOST)==0){
        changeLives(LOSE_LIVE_ID);
        createJavaClientMessage(LOSE_LIVE);
    }
    else if(strcmp(messageID,DESPAWN_PINKY)==0){
        changeGhostStatus(PINKY_ID);
    }
    else if(strcmp(messageID,DESPAWN_INKY)==0){
        changeGhostStatus(INKY_ID);
    }
    else if(strcmp(messageID,DESPAWN_BLINKY)==0){
        changeGhostStatus(BLINKY_ID);
    }
    else if(strcmp(messageID,DESPAWN_CLYDE)==0){
        changeGhostStatus(CLYDE_ID);
    }
    return 0;
}