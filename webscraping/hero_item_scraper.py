import json
import re
import requests
from bs4 import BeautifulSoup


def tag_with_text(tag, type, regex):
    return tag.name in type and tag.get_text() and re.search(regex, tag.get_text())


base_url = "https://imperial-assault.fandom.com"
homepage = base_url + "/wiki/Hero"
page = requests.get(homepage)
soup = BeautifulSoup(page.text, "html.parser")

agendas = []
# Easy to reuse for other sections
l = soup.article.find(lambda tag: tag_with_text(tag, ["h2"], re.compile("(?i)expansions?:?")))

rewards = []
for wave in l.find_next_siblings("ul"):
    wavename = wave.find_previous_sibling("h3").get_text().replace("Edit", "").strip()
    for hero in wave.find_all("li"):
        links = hero.find_all('a')
        hero = links[0].string.strip()
        cardpage = requests.get(base_url+links[0]["href"])
        cardsoup = BeautifulSoup(cardpage.text, "html.parser")
        try:
            target = cardsoup.find("caption", string=re.compile("(?i)weapon")).parent.parent
            while target:
                cards = target.find_all("a")
                for card in cards:
                    card = {
                        "hero": hero,
                        "name": card.string.strip()
                    }
                    rewards.append(card)
                target = target.find_next_sibling("section")
                if "Skill" not in target.find("caption").string:
                    break
        except Exception as e:
            print("{}: {}".format(hero, e))

with open("hero_items.json", "w") as newfile:
    newfile.write(json.dumps(rewards, indent=4, sort_keys=True))
