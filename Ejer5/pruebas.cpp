#include <iostream>
using namespace std;

char filas(char v[][3])
{
    int x = 0;
    int o = 0;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (v[i][j] == 'X')
                x++;
            else if (v[i][j] == 'O')
                o++;
        }
        if (x == 3)
            return 'X';
        if (o == 3)
            return 'O';
        x = o = 0;
    }
    cout << "x=" << x << "\n o=" << o << endl;
    if (x == 3)
        return 'X';
    if (o == 3)
        return 'O';
    return NULL;
}

char cols(char v[][3])
{
    int x = 0;
    int o = 0;
    for (int j = 0; j < 3; j++)
    {
        for (int i = 0; i < 3; i++)
        {
            if (v[i][j] == 'X')
                x++;
            else if (v[i][j] == 'O')
                o++;
        }
        if (x == 3)
            return 'X';
        if (o == 3)
            return 'O';

        x = o = 0;
    }
    cout << "x=" << x << "\n o=" << o << endl;
    return NULL;
}

char diagonal1(char v[][3])
{
    int x = 0;
    int o = 0;
    for (int j = 0; j < 3; j++)
        if (v[j][j] == 'X')
            x++;
        else if (v[j][j] == 'O')
            o++;
    if (x == 3)
        return 'X';
    if (o == 3)
        return 'O';
    return NULL;
}

char diagonal2(char v[][3])
{
    int x = 0;
    int o = 0;
    int j;
    for (int i = 2, j=0; i >=0; i--, j++) {
        if (v[i][j] == 'X')
            x++;
        else if (v[i][j] == 'O')
            o++;
        cout << "(" << i<< ", " << j<< ")" << endl;
        
        
    }
    if (x == 3)
        return 'X';
    if (o == 3)
        return 'O';
    return NULL;
}

bool ganador(char v[][3]) {
    return diagonal1(v) || diagonal2(v) || cols(v) || filas(v);
}

int main()
{
    char t[][3] = {
        {'X', 'X', 'O'},
        {'_', 'X', '_'},
        {'O', 'O', 'X'},
        };

    cout << boolalpha<< ganador(t) << endl;

    return 0;
}