import json
import re
import requests
from bs4 import BeautifulSoup


def tag_with_text(tag, type, regex):
    return tag.name in type and tag.get_text() and re.search(regex, tag.get_text())


base_url = "https://imperial-assault.fandom.com"
homepage = base_url + "/wiki/Agenda_Set"
page = requests.get(homepage)
soup = BeautifulSoup(page.text, "html.parser")

agendas = []
# Easy to reuse for other sections
l = soup.article.find(lambda tag: tag_with_text(tag, ["h2"], re.compile("(?i)expansions?:?")))

rewards = []
for wave in l.find_next_siblings("ul"):
    wavename = wave.find_previous_sibling("h3").get_text().replace("Edit", "").strip()
    for agenda in wave.find_all("li"):
        links = agenda.find_all('a')
        agenda = links[0].string.strip()
        if "Wave" in wavename:
            expansion = links[-1].string.strip()
        else:
            expansion = wavename
        cardpage = requests.get(base_url+links[0]["href"])
        cardsoup = BeautifulSoup(cardpage.text, "html.parser")
        try:
            target = cardsoup.find("h2", string="Agenda Cards")
            for card in target.find_next_siblings("div"):
                card = {
                    "agenda": agenda,
                    "expansion": expansion,
                    "name": card.get_text().strip()
                }
                rewards.append(card)
        except AttributeError as e:
            try:
                target = cardsoup.find(lambda tag: tag_with_text(tag, ["h2","h3"], re.compile("(?i)cards")))
                for card in target.find_next_sibling("table").find_all("tr"):
                    card = {
                        "agenda": agenda,
                        "expansion": expansion,
                        "name": card.find("h3").get_text().replace("Edit", "").strip()
                    }
                    rewards.append(card)
            except Exception as e:
                print("{}: {}".format(agenda, e))
        except Exception as e:
            print("{}: {}".format(agenda, e))
            

with open("agenda_sets.json", "w") as newfile:
    newfile.write(json.dumps(rewards, indent=4, sort_keys=True))
