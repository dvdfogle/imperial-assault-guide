import json
import re
import requests
from bs4 import BeautifulSoup


def tag_with_text(tag, type, regex):
    return tag.name in type and tag.get_text() and re.search(regex, tag.get_text())


base_url = "https://imperial-assault.fandom.com"
homepage = base_url + "/wiki/Item_Card_-_By_Tier/Product"
page = requests.get(homepage)
soup = BeautifulSoup(page.text, "html.parser")

agendas = []
# Easy to reuse for other sections
l = soup.article.find(lambda tag: tag_with_text(tag, ["h2"], re.compile("(?i)by tier")))

rewards = []
for wave in l.find_next_siblings("ul"):
    tier = wave.find_previous_sibling("h3").get_text().strip().replace("Edit", "")
    for agenda in wave.find_all("li"):
        try:
            link = agenda.find('a')
            card = {
                "card_name": link.string.strip(),
                "expansion": link.next_sibling.strip(),
                "tier": tier
            }
            rewards.append(card)
        except Exception as e:
            print("{}: {}".format(agenda, e))
            a = agenda.contents[0]
            card = {
                "name": a.string.strip(),
                "side_mission": a.find_next_sibling("i").find("a").string.strip()
            }
            rewards.append(card)
with open("items.json", "w") as newfile:
    newfile.write(json.dumps(rewards, indent=4, sort_keys=True))
