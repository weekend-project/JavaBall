import requests
import json
import os

# The current year should always be the previous year (for the 2021 season, use 2020). This is an issue with MLB.com
year = '2020'

all_players = requests.get(
    'https://statsapi.mlb.com/api/v1/sports/1/players?fields=people,fullName,lastName,nameSlug&season=' + year)
people_dict = json.loads(all_players.text)
players_dict = people_dict['people']

if os.path.isfile('./player_name_slugs/nameslug_'+year+'.txt'):
    print('The file "nameslug_'+year+'.txt" already exists!')
else:
    file = open("./player_name_slugs/nameslug_"+year+".txt", "w")
    for player in players_dict:
        player_fullname = player['fullName']
        player_nameslug = player['nameSlug']
        file.write(player_fullname)
        file.write(", ")
        file.write(player_nameslug)
        file.write("\n")
    file.close()

# This is a visual reference for the structure of the JSON nested dictionary that is being imported
#
# json_dict = {
#     'people': [
#         {
#             'fullName': 'Albert Abreu',
#             'lastName': 'Abreu',
#             'nameSlug': 'albert-abreu-656061'
#         },
#         {
#             'fullName': 'Bryan Abreu',
#             'lastName': 'Abreu',
#             'nameSlug': 'bryan-abreu-650556'
#         },
#         {
#             'fullName': 'Mike Zunino',
#             'lastName': 'Zunino',
#             'nameSlug': 'mike-zunino-572287'
#         }
#     ]
# }
# json_dict['people'][1]['nameSlug'] = bryan-abreu-650556
