class Node:
    def __init__(self, name, parent=None):
        self.name = name
        self.parent = parent
        self.children = []

        if parent:
            self.parent.children.append(self)


def print_tree(current_node, childattr='children', nameattr='name', indent='', last='updown'):

    if hasattr(current_node, nameattr):
        name = lambda node: getattr(node, nameattr)
    else:
        name = lambda node: str(node)

    children = lambda node: getattr(node, childattr)
    nb_children = lambda node: sum(nb_children(child) for child in children(node)) + 1
    size_branch = {child: nb_children(child) for child in children(current_node)}

    up = sorted(children(current_node), key=lambda node: nb_children(node))
    down = []
    while up and sum(size_branch[node] for node in down) < sum(size_branch[node] for node in up):
        down.append(up.pop())

    for child in up:
        next_last = 'up' if up.index(child) is 0 else ''
        next_indent = '{0}{1}{2}'.format(indent, ' ' if 'up' in last else '│', ' ' * len(name(current_node)))
        print_tree(child, childattr, nameattr, next_indent, next_last)

    if last == 'up': start_shape = '┌'
    elif last == 'down': start_shape = '└'
    elif last == 'updown': start_shape = ' '
    else: start_shape = '├'

    if up: end_shape = '┤'
    elif down: end_shape = '┐'
    else: end_shape = ''

    print('{0}{1}{2}{3}'.format(indent, start_shape, name(current_node), end_shape))

    for child in down:
        next_last = 'down' if down.index(child) is len(down) - 1 else ''
        next_indent = '{0}{1}{2}'.format(indent, ' ' if 'down' in last else '│', ' ' * len(name(current_node)))
        print_tree(child, childattr, nameattr, next_indent, next_last)

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 10 22:14:31 2019
@author: Khewbs
"""

def getNext(root):
    root = __builtins__.list(root)
    next = []
    index = root.index('0')
    tmp = root[:]
    try:
        if (tmp[index+1] == '2'):
            tmp[index] = '2'
            tmp[index+1] = '0'
            str = ''.join(tmp)
            next.append(str)
    except:
        None
    try:
        tmp = root[:]
        if (tmp[index+2] == '2'):
            tmp[index] = '2'
            tmp[index+2] = '0'
            str = ''.join(tmp)
            next.append(str)
    except:
        None
    try:
        tmp = root[:]
        if (index-1 >= 0 and tmp[index-1] == '1'):
            tmp[index] = '1'
            tmp[index-1] = '0'
            str = ''.join(tmp)
            next.append(str)
    except:
        None
    try:
        tmp = root[:]
        if (index-2 >= 0 and tmp[index-2] == '1'):
            tmp[index] = '1'
            tmp[index-2] = '0'
            str = ''.join(tmp)
            next.append(str)
    except:
        None
    return next

"""Main"""    
root = Node("1110222")
stack = ['1110222']
stackNodes = [root]
while len(stack)!=0:
    node = stack.pop()
    prevNode = stackNodes.pop()
    tmp = getNext(node)
    stack = stack + tmp
    for nodes in tmp:
        stackNodes.append(Node(nodes,prevNode))

print_tree(root)
