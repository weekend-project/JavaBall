from bs4 import BeautifulSoup
import requests
import os.path

# TODO add functionality that allows any user to change team_name
team_name = 'padres'

depth_chart_html_text = requests.get('https://www.mlb.com/' + team_name + '/roster/depth-chart').text
depth_chart = BeautifulSoup(depth_chart_html_text, 'lxml')

# index 0: player name
# index 1: jersey number
# index 2: player b/t
rotation = [[], [], []]
bullpen = [[], [], []]
catcher = [[], [], []]
first_base = [[], [], []]
second_base = [[], [], []]
third_base = [[], [], []]
shortstop = [[], [], []]
left_field = [[], [], []]
center_field = [[], [], []]
right_field = [[], [], []]

positions = [rotation,
             bullpen,
             catcher,
             first_base,
             second_base,
             third_base,
             shortstop,
             left_field,
             center_field,
             right_field]

# This is only needed for the `write_to_file` function, is it possible to print the name of a list instead?
position_strings = ['rotation',
                    'bullpen',
                    'catcher',
                    'first_base',
                    'second_base',
                    'third_base',
                    'shortstop',
                    'left_field',
                    'center_field',
                    'right_field']

# Building the lists of player names, jersey numbers, and their b/t
for position in depth_chart.find_all('table', class_='roster__table'):
    player_name = position.find_all('a')
    player_number = position.find_all('span', class_='jersey')
    player_bt = position.find_all('td', class_='bat-throw')

# TODO can you put all of these if statements into one function?
    for name in player_name:
        if position.find('td').text == 'Rotation':
            rotation[0].append(name.text)
        if position.find('td').text == 'Bullpen':
            bullpen[0].append(name.text)
        if position.find('td').text == 'Catcher':
            catcher[0].append(name.text)
        if position.find('td').text == 'First Base':
            first_base[0].append(name.text)
        if position.find('td').text == 'Second Base':
            second_base[0].append(name.text)
        if position.find('td').text == 'Third Base':
            third_base[0].append(name.text)
        if position.find('td').text == 'Shortstop':
            shortstop[0].append(name.text)
        if position.find('td').text == 'Left Field':
            left_field[0].append(name.text)
        if position.find('td').text == 'Center Field':
            center_field[0].append(name.text)
        if position.find('td').text == 'Right Field':
            right_field[0].append(name.text)

    for number in player_number:
        if position.find('td').text == 'Rotation':
            rotation[1].append(number.text)
        if position.find('td').text == 'Bullpen':
            bullpen[1].append(number.text)
        if position.find('td').text == 'Catcher':
            catcher[1].append(number.text)
        if position.find('td').text == 'First Base':
            first_base[1].append(number.text)
        if position.find('td').text == 'Second Base':
            second_base[1].append(number.text)
        if position.find('td').text == 'Third Base':
            third_base[1].append(number.text)
        if position.find('td').text == 'Shortstop':
            shortstop[1].append(number.text)
        if position.find('td').text == 'Left Field':
            left_field[1].append(number.text)
        if position.find('td').text == 'Center Field':
            center_field[1].append(number.text)
        if position.find('td').text == 'Right Field':
            right_field[1].append(number.text)

    for bt in player_bt:
        if bt.text != 'B/T':
            if position.find('td').text == 'Rotation':
                rotation[2].append(bt.text)
            if position.find('td').text == 'Bullpen':
                bullpen[2].append(bt.text)
            if position.find('td').text == 'Catcher':
                catcher[2].append(bt.text)
            if position.find('td').text == 'First Base':
                first_base[2].append(bt.text)
            if position.find('td').text == 'Second Base':
                second_base[2].append(bt.text)
            if position.find('td').text == 'Third Base':
                third_base[2].append(bt.text)
            if position.find('td').text == 'Shortstop':
                shortstop[2].append(bt.text)
            if position.find('td').text == 'Left Field':
                left_field[2].append(bt.text)
            if position.find('td').text == 'Center Field':
                center_field[2].append(bt.text)
            if position.find('td').text == 'Right Field':
                right_field[2].append(bt.text)


# TODO is there a better way to iterate through the `position_strings` list?
def write_to_file(chart, iterator):
    chart_length = len(chart[0])
    counter = 0
    file.write(position_strings[iterator])
    file.write("\n")
    for array in chart:
        while counter < chart_length:
            file.write(chart[0][counter])
            file.write(",")
            file.write(chart[1][counter])
            file.write(",")
            file.write(chart[2][counter])
            file.write("\n")
            counter += 1


# Creating and writing team names to file
if os.path.isfile('./team_depth_charts/' + team_name + '_depth_chart.txt'):
    print('The file "' + team_name + '_depth_chart.txt" already exists!')
else:
    file = open("./team_depth_charts/" + team_name + "_depth_chart.txt", "w")
    string_iterator = 0
    for pos in positions:
        write_to_file(pos, string_iterator)
        string_iterator += 1
    file.close()
    print('File "' + team_name + '_depth_chart.txt" successfully created!')

# print('Rotation: ', end=" ")
# print(rotation)
# print('Bullpen: ', end=" ")
# print(bullpen)
# print('Catcher: ', end=" ")
# print(catcher)
# print('First Base: ', end=" ")
# print(first_base)
# print('Second Base: ', end=" ")
# print(second_base)
# print('Third Base: ', end=" ")
# print(third_base)
# print('Shortstop: ', end=" ")
# print(shortstop)
# print('Left Field: ', end=" ")
# print(left_field)
# print('Center Field: ', end=" ")
# print(center_field)
# print('Right Field: ', end=" ")
# print(right_field)
