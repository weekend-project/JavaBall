from bs4 import BeautifulSoup
import requests
import os.path

year = '2021'
team_names_html_text = requests.get('https://www.mlb.com/team').text
team_names = BeautifulSoup(team_names_html_text, 'lxml')

# index 0: American league
# index 1: National league
teams_list = [[], []]

# Building the list of team names, separating AL teams and NL teams (15 teams in each league, as of writing)
counter = 0
for team in team_names.find_all('div', class_='u-text-h4 u-text-flow'):
    if counter < 15:
        teams_list[0].append(team.text)
        counter += 1
    else:
        teams_list[1].append(team.text)

# Creating and writing team names to file
if os.path.isfile('./team_names/team_names_'+year+'.txt'):
    print('The file "team_names_'+year+'.txt" already exists!')
else:
    file = open("./team_names/team_names_"+year+".txt", "w")
    file.write("AL:")
    file.write("\n")
    for team in teams_list[0]:
        file.write(team)
        file.write("\n")
    file.write("NL:")
    file.write("\n")
    for team in teams_list[1]:
        file.write(team)
        file.write("\n")
    file.close()
