# CursedTexturePackCreator
Creates cursed texture packs for minecraft
## How to use
- navigate to ``%appdata%\.minecraft\resourcepacks`` and create a new folder, name it something.
- go back to ``.minecraft`` and open ``versions``, locate the folder of the version you want to texture pack to work on, open the folder, right click the ``.jar`` and open it with winrar or 7-zip
- copy the entire ``assets`` folder from winrar or 7-zip into the resourcepack folder you created earlier
- copy the ``assets`` folder in ``.minecraft\resourcepacks\your resource pack`` and name the copy ``default_assets``
- open ``assets\minecraft`` and delete the ``textures`` folder
- edit the java file and change ``CursedMinecraft`` to the name of the folder you made
- run this program and it should copy and scramble the textures from ``default_assets`` to ``assets``
- after the program is finished, create a new file called ``pack.mcmeta`` in along side the ``assets`` folder, open ``pack.mcmeta`` with a program like notepad++, the content should be like the following:
```
{
    "pack": {
        "pack_format": 4,
        "description": "description of the texturepack shown in minecraft"
    }
}
```
- save and close ``pack.mcmeta`` and launch minecraft, go to options - texturepacks and apply the new pack
- enjoy cursed minecraft
